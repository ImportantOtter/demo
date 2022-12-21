CREATE TABLE IF NOT EXISTS discount_card (
    id bigserial primary key,
    discount integer
);

CREATE TABLE IF NOT EXISTS product (
    id bigserial primary key,
    name varchar(255),
    price double precision,
    promotional_product boolean
);