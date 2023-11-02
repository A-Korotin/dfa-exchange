--liquibase formatted sql

--changeset Alexey Korotin:0001

CREATE TABLE wallet (
    id uuid PRIMARY KEY,
    user_id TEXT NOT NULL
);