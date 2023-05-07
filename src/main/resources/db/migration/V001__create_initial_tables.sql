create table coupon (
    id uuid primary key,
    code varchar unique not null,
    percentage numeric not null,
    expiration_at timestamp with time zone not null
);

create table product (
    id uuid primary key,
    description varchar not null,
    height numeric not null,
    width numeric not null,
    "length" numeric not null,
    weight numeric not null
);

create table "order" (
    id uuid primary key,
    order_number varchar not null,
    document varchar not null,
    total_amount numeric not null,
    coupon_code varchar
);

create table order_item (
    id uuid primary key,
    order_id uuid not null,
    product_id uuid not null,
    price numeric not null,
    quantity numeric not null,
    FOREIGN KEY (order_id) REFERENCES "order" (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);
