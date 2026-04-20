-- liquibase formatted sql

-- changeset Denis:init-1
DROP SCHEMA IF EXISTS auth_service CASCADE;
DROP USER IF EXISTS auth_service;
CREATE USER auth_service WITH PASSWORD 'auth_service';
CREATE SCHEMA auth_service;
GRANT USAGE ON SCHEMA auth_service TO auth_service;
ALTER DEFAULT PRIVILEGES IN SCHEMA auth_service GRANT ALL ON TABLES TO auth_service;
ALTER DEFAULT PRIVILEGES IN SCHEMA auth_service GRANT ALL ON SEQUENCES TO auth_service;

-- changeset Denis:init-2
DROP SCHEMA IF EXISTS list_product_service CASCADE;
DROP USER IF EXISTS list_product_service;
CREATE USER list_product_service WITH PASSWORD 'list_product_service';
CREATE SCHEMA list_product_service;
GRANT USAGE ON SCHEMA list_product_service TO list_product_service;
ALTER DEFAULT PRIVILEGES IN SCHEMA list_product_service GRANT ALL ON TABLES TO list_product_service;
ALTER DEFAULT PRIVILEGES IN SCHEMA list_product_service GRANT ALL ON SEQUENCES TO list_product_service;