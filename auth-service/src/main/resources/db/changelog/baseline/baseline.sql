-- liquibase formatted sql

-- changeset Denis:baseline-1
CREATE TABLE IF NOT EXISTS auth_service.users
(
    id       bigserial NOT NULL PRIMARY KEY,
    username varchar   NOT NULL,
    password text      NOT NULL,
    phone    bigint    NOT NULL,
    email    text,
    role     varchar   NOT NULL,
    image    text,
    CONSTRAINT phone_u UNIQUE (phone)
);

ALTER TABLE IF EXISTS auth_service.users
    OWNER to auth_service;