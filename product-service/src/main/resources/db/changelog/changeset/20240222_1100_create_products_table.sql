create table if not exists products
(
    id          uuid         not null primary key default gen_random_uuid(),
    name        varchar(255) not null,
    description text         not null,
    price       float        not null check ( price > 0 ),
    quantity    int          not null check ( quantity > -1 )
);

insert into products
values ('04faa1cb-4fa3-49eb-9d0b-c0df47e3075c',
        'iPhone 11',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        200000.0,
        15),
       ('c8b2a7be-6c0a-49cb-a2d6-438937b3e14d',
        'Xiaomi Redmi Note 11 Pro',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        100000.0,
        44),
       ('04942224-8a58-4909-a63a-97ad080cb79f',
        'Football Ronaldo T-shirt',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        10000.0,
        78);
