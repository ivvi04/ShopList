-- liquibase formatted sql

-- changeset Denis:baseline-1
------------------- Enumerators -------------------

CREATE TYPE list_product_service.status_enum AS ENUM
    ('CREATED', 'UPDATED', 'DELETED');

ALTER TYPE list_product_service.status_enum
    OWNER TO list_product_service;

--------------------- Tables ---------------------

CREATE TABLE IF NOT EXISTS list_product_service.lists
(
    id          bigserial       PRIMARY KEY NOT NULL,
    name        text                        NOT NULL,
    author_id   bigint                      NOT NULL,
    status      status_enum                 NOT NULL
);

ALTER TABLE IF EXISTS list_product_service.lists
    OWNER to list_product_service;

CREATE TABLE IF NOT EXISTS list_product_service.user_list
(
    id          bigserial       PRIMARY KEY NOT NULL,
    user_id     bigint                      NOT NULL,
    list_id     bigint                      NOT NULL,
    CONSTRAINT list_id_fkey     FOREIGN KEY (list_id)
    REFERENCES list_product_service.lists (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS list_product_service.user_list
    OWNER to list_product_service;

CREATE TABLE IF NOT EXISTS list_product_service.product
(
    id          bigserial       PRIMARY KEY NOT NULL,
    name        text                        NOT NULL,
    price       numeric,
    url         text,
    list_id     bigint                      NOT NULL,
    image       text,
    purchased   boolean         NOT NULL DEFAULT false,
    CONSTRAINT name_list_u      UNIQUE (name, list_id),
    CONSTRAINT list_id_fkey     FOREIGN KEY (list_id)
    REFERENCES list_product_service.lists (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );

ALTER TABLE IF EXISTS list_product_service.product
    OWNER to list_product_service;