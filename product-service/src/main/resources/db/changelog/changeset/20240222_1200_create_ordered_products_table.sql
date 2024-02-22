create table if not exists ordered_products
(
    id uuid not null primary key default gen_random_uuid(),
    order_id uuid not null references orders(id),
    product_id uuid not null references products(id),
    quantity int not null check ( quantity > 0 )
);

insert into ordered_products
values ('3558c3c5-c3bd-4ba9-a765-a90619e62ade',
        '1c11a396-5dec-4981-969c-8d5c3626c45a',
        '04faa1cb-4fa3-49eb-9d0b-c0df47e3075c',
        10);