create table if not exists orders
(
    id             uuid         not null primary key default gen_random_uuid(),
    customer_email varchar(255) not null,
    ordered_at     timestamp                         default current_timestamp,
    status         varchar(20)  not null check ( status in ('PENDING', 'SHIPPED', 'DELIVERED') )
);

insert into orders(id, customer_email, status)
values ('1c11a396-5dec-4981-969c-8d5c3626c45a', 'asulanma@gmail.com', 'PENDING');