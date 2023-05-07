create table coupons (
    id uuid primary key,
    code varchar unique not null,
    percentage numeric not null,
    expiration_at timestamp with time zone not null
);

CREATE INDEX idx_coupon_code ON coupons (code);

create table products (
    id uuid primary key,
    description varchar not null,
    height numeric not null,
    width numeric not null,
    "length" numeric not null,
    weight numeric not null
);

create table orders (
    id uuid primary key,
    order_number varchar not null,
    document varchar not null,
    total_amount numeric not null,
    coupon_id UUID,
    created_at timestamp with time zone not null,
    FOREIGN KEY (coupon_id) REFERENCES coupons (id)
);

CREATE INDEX idx_orders_created_at ON orders (created_at);

create table items (
    id uuid primary key,
    order_id uuid not null,
    product_id uuid not null,
    price numeric not null,
    quantity numeric not null,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);
