--liquibase formatted sql

--changeset Alexey Korotin:0006

ALTER TABLE limit_order
    ADD COLUMN volume INTEGER;

ALTER TABLE limit_order
    ADD COLUMN volume_executed INTEGER DEFAULT 0;

ALTER TABLE market_order
    ADD COLUMN volume INTEGER;

ALTER TABLE market_order
    ADD COLUMN volume_executed INTEGER DEFAULT 0;
