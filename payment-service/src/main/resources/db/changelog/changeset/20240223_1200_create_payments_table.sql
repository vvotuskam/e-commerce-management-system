create table if not exists payments
(
    id           uuid      not null primary key default gen_random_uuid(),
    email        varchar   not null,
    amount       float     not null,
    payment_date timestamp not null,
    order_id     varchar   not null
);