--liquibase formatted sql

--changeset Alexey Korotin:0005

CREATE TABLE limit_order (
    id uuid PRIMARY KEY,
    dtype TEXT NOT NULL,
    status TEXT NOT NULL,
    timestamp timestamp NOT NULL,
    from_address TEXT REFERENCES account(address),
    to_address TEXT REFERENCES account(address),
    buy_asset_ticker VARCHAR(10) NOT NULL,
    buy_asset_chain VARCHAR(255) NOT NULL,
    sell_asset_ticker VARCHAR(10) NOT NULL,
    sell_asset_chain VARCHAR(255) NOT NULL,
    price INTEGER NOT NULL
);