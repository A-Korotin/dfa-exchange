--liquibase formatted sql

--changeset Alexey Korotin:0002

CREATE TABLE account (
    address TEXT PRIMARY KEY,
    private_key TEXT NOT NULL,
    alias TEXT,
    chain_name VARCHAR(255),
    wallet_id uuid REFERENCES wallet(id)
);