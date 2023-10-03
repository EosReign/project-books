--liquibase formatted sql

--changeSet eosreign:1
CREATE TABLE client_table
(
    id              SERIAL          NOT NULL PRIMARY KEY,
    client_name     VARCHAR(64)     NOT NULL,
    client_address  VARCHAR(128)    NOT NULL,
    client_phone    INT8            NOT NULL UNIQUE,
    password        TEXT            NOT NULL
);

--changeSet eosreign:2
CREATE TABLE book_table
(
    id            SERIAL        NOT NULL PRIMARY KEY,
    serial_number VARCHAR(17)   NOT NULL UNIQUE,
    book_name     VARCHAR(64)   NOT NULL,
    book_text     TEXT          NOT NULL,
    author_name   VARCHAR(64)   NOT NULL,
    client_id     INT8
);

--changeSet eosreign:3
CREATE TABLE transaction_table
(
    id           SERIAL     PRIMARY KEY,
    date_take    DATE       NOT NULL,
    date_return  DATE       NOT NULL,
    client_id    INT8       NOT NULL,
    book_id      INT8       NOT NULL,
    is_closed    BOOLEAN    NOT NULL
);

--changeSet eosreign:4
CREATE TABLE authorities
(
    id        SERIAL        NOT NULL PRIMARY KEY,
    phone     INT8          NOT NULL UNIQUE,
    authority VARCHAR(30)   NOT NULL
);


