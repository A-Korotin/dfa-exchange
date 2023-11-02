--liquibase formatted sql

--changeset Alexey Korotin:0003

CREATE TABLE "transaction" (
    id uuid PRIMARY KEY,
    sender_address TEXT REFERENCES account(address),
    receiver_address TEXT REFERENCES account(address),
    signature TEXT NOT NULL,
    asset_ticker VARCHAR(10) NOT NULL,
    asset_chain VARCHAR(255) NOT NULL,
    amount INTEGER NOT NULL,
    status TEXT NOT NULL,
    type TEXT NOT NULL,
    timestamp timestamptz NOT NULL
);