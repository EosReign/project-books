--liquibase formatted sql

--changeSet eosreign:1
CREATE TABLE client_table
(
    id              SERIAL          NOT NULL PRIMARY KEY,
    client_name     VARCHAR(64)     NOT NULL,
    client_address  VARCHAR(128)    NOT NULL,
    client_phone    INT8            NOT NULL UNIQUE
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
CREATE TABLE history_table
(
    id           SERIAL     PRIMARY KEY,
    date_take    DATE       NOT NULL,
    date_return  DATE       NOT NULL,
    client_id    INT8       ,
    book_id      INT8
);


