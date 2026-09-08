-- liquibase formatted sql

-- changeset Denis:baseline-1
CREATE TABLE IF NOT EXISTS list_product_service.lists
(
    id     bigserial PRIMARY KEY NOT NULL,
    name   text                  NOT NULL,
    status varchar               NOT NULL
);

ALTER TABLE IF EXISTS list_product_service.lists
    OWNER to list_product_service;

CREATE TABLE IF NOT EXISTS list_product_service.list_users
(
    list_id    bigint  NOT NULL,
    user_phone bigint  NOT NULL,
    is_author  boolean NOT NULL DEFAULT false,
    CONSTRAINT list_id_user_phone_pkey PRIMARY KEY (list_id, user_phone),
    CONSTRAINT list_id_fkey FOREIGN KEY (list_id) REFERENCES list_product_service.lists (id)
);

ALTER TABLE IF EXISTS list_product_service.list_users
    OWNER to list_product_service;

CREATE TABLE IF NOT EXISTS list_product_service.products
(
    id        bigserial PRIMARY KEY NOT NULL,
    list_id   bigint                NOT NULL,
    name      text                  NOT NULL,
    price     numeric,
    url       text,
    image     text,
    purchased boolean               NOT NULL DEFAULT false,
    CONSTRAINT name_list_id_u UNIQUE (name, list_id),
    CONSTRAINT list_id_fkey FOREIGN KEY (list_id) REFERENCES list_product_service.lists (id)
);

ALTER TABLE IF EXISTS list_product_service.products
    OWNER to list_product_service;