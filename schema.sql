------------------- Enumerators -------------------

CREATE TYPE public.role_enum AS ENUM
    ('ADMIN', 'USER');

ALTER TYPE public.role_enum
    OWNER TO postgres;

CREATE TYPE public.status_enum AS ENUM
    ('CREATED', 'UPDATED', 'DELETED');

ALTER TYPE public.status_enum
    OWNER TO postgres;

--------------------- Tables ---------------------

CREATE TABLE IF NOT EXISTS public."user"
(
    id          bigserial                                       NOT NULL,
    username    character(100)  COLLATE pg_catalog."default"    NOT NULL,
    password    text            COLLATE pg_catalog."default"    NOT NULL,
    phone       bigint                                          NOT NULL,
    email       character(100)  COLLATE pg_catalog."default",
    role        role_enum                                       NOT NULL,
    image       text            COLLATE pg_catalog."default",
    CONSTRAINT user_pkey        PRIMARY KEY (id),
    CONSTRAINT phone_u          UNIQUE (phone)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."user"
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.lists
(
    id          bigserial                                       NOT NULL,
    name        text            COLLATE pg_catalog."default"    NOT NULL,
    author_id   bigint                                          NOT NULL,
    status      status_enum                                     NOT NULL,
    CONSTRAINT list_pkey        PRIMARY KEY (id),
    CONSTRAINT user_id_fkey     FOREIGN KEY (author_id)
    REFERENCES public."user" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.lists
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.user_list
(
    id          bigserial       NOT NULL,
    user_id     bigint          NOT NULL,
    list_id     bigint          NOT NULL,
    CONSTRAINT user_list_pkey   PRIMARY KEY (id),
    CONSTRAINT list_id_fkey     FOREIGN KEY (list_id)
    REFERENCES public.lists (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT user_id_fkey     FOREIGN KEY (user_id)
    REFERENCES public."user" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_list
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.product
(
    id          bigserial                                       NOT NULL,
    name        text            COLLATE pg_catalog."default"    NOT NULL,
    price       numeric,
    url         text            COLLATE pg_catalog."default",
    list_id     bigint                                          NOT NULL,
    image       text            COLLATE pg_catalog."default",
    purchased   boolean                                         NOT NULL DEFAULT false,
    CONSTRAINT product_pkey     PRIMARY KEY (id),
    CONSTRAINT name_list_u      UNIQUE (name, list_id),
    CONSTRAINT list_id_fkey     FOREIGN KEY (list_id)
    REFERENCES public.lists (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product
    OWNER to postgres;