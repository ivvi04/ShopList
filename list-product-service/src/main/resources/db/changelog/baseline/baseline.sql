-- liquibase formatted sql

-- changeset Denis:baseline-1
CREATE TABLE IF NOT EXISTS list_product_service.shop_lists
(
    id          bigserial       PRIMARY KEY NOT NULL,
    name        text                        NOT NULL,
    status      varchar                     NOT NULL
);

ALTER TABLE IF EXISTS list_product_service.shop_lists OWNER to list_product_service;

CREATE TABLE IF NOT EXISTS list_product_service.shop_list_users
(
    id           bigserial       PRIMARY KEY NOT NULL,
    user_id      bigint                      NOT NULL,
    is_author    boolean                     NOT NULL DEFAULT false,
    shop_list_id bigint                      NOT NULL,
    CONSTRAINT shop_list_id_fkey FOREIGN KEY (shop_list_id) REFERENCES list_product_service.shop_lists (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS list_product_service.user_list_links OWNER to list_product_service;

CREATE TABLE IF NOT EXISTS list_product_service.products
(
    id           bigserial       PRIMARY KEY NOT NULL,
    name         text                        NOT NULL,
    price        numeric,
    url          text,
    shop_list_id bigint                      NOT NULL,
    image        text,
    purchased    boolean                     NOT NULL DEFAULT false,
    CONSTRAINT name_shop_list_id_u UNIQUE (name, shop_list_id),
    CONSTRAINT shop_list_id_fkey FOREIGN KEY (shop_list_id) REFERENCES list_product_service.shop_lists (id)
    );

ALTER TABLE IF EXISTS list_product_service.products OWNER to list_product_service;