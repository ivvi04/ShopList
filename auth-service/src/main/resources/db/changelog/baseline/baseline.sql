-- liquibase formatted sql

-- changeset Denis:baseline-1
------------------- Enumerators -------------------

CREATE TYPE auth_service.role_enum AS ENUM
    ('ADMIN', 'USER');

ALTER TYPE auth_service.role_enum
    OWNER TO auth_service;

--------------------- Tables ---------------------
DROP TABLE IF EXISTS auth_service."user";

CREATE TABLE IF NOT EXISTS auth_service."user" (
    id          bigserial                       NOT NULL PRIMARY KEY,
    username    character(100)                  NOT NULL,
    password    text                            NOT NULL,
    phone       bigint                          NOT NULL,
    email       character(100)                          ,
    role        auth_service.role_enum          NOT NULL,
    image       text                                    ,
    CONSTRAINT phone_u UNIQUE (phone)
    );

ALTER TABLE IF EXISTS auth_service."user"
    OWNER to auth_service;