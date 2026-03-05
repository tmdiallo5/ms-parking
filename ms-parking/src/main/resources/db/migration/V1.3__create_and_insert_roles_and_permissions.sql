create table roles (
    id int primary key auto_increment,
    name varchar(40) not null unique,
    description varchar(255),
    creation datetime default current_timestamp
);

insert into roles(name)
values ('ADMINISTRATOR'),
       ('OWNER'),
       ('CLIENT');


create table permissions(
        id int primary key auto_increment,
        name varchar(40) not null unique,
        description varchar(255)
);

insert into permissions(name)
values
    ('PARKING_CREATE'),
    ('PARKING_READ'),
    ('PARKING_UPDATE'),
    ('PARKING_DISABLE'),

    ('SPOT_CREATE'),
    ('SPOT_READ'),
    ('SPOT_UPDATE'),
    ('SPOT_DISABLE'),

    ('RESERVATION_CREATE'),
    ('RESERVATION_READ'),
    ('RESERVATION_UPDATE'),
    ('RESERVATION_CANCEL'),

    ('PAYMENT_CREATE'),
    ('PAYMENT_READ'),
    ('PAYMENT_DISABLE'),

    ('PROFILE_CREATE'),
    ('PROFILE_READ'),
    ('PROFILE_UPDATE'),
    ('PROFILE_DISABLE'),

    ('ROLE_CREATE'),
    ('ROLE_READ'),
    ('ROLE_UPDATE'),
    ('ROLE_DISABLE'),
    ('ROLE_ASSIGN'),
    ('PERMISSION_READ'),
    ('PERMISSION_ASSIGN_TO_ROLE');



create table roles_permissions(
    id int primary key auto_increment,
    role_id int,
    permission_id int,
    constraint fk_role_id foreign key (role_id) references roles(id) on delete cascade,
    constraint fk_permission_id foreign key (permission_id) references permissions(id) on delete cascade,
    constraint unique_role_permission unique(role_id, permission_id)
);

insert into roles_permissions(role_id, permission_id)
values
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PARKING_CREATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PARKING_READ')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PARKING_UPDATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PARKING_DISABLE')),

    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'SPOT_CREATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'SPOT_READ')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'SPOT_UPDATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'SPOT_DISABLE')),

    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'RESERVATION_READ')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'RESERVATION_UPDATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'RESERVATION_CANCEL')),

    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PAYMENT_READ')),

    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PROFILE_READ')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PROFILE_UPDATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PROFILE_DISABLE')),

    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'ROLE_CREATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'ROLE_READ')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'ROLE_UPDATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'ROLE_DISABLE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'ROLE_ASSIGN')),

    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PERMISSION_READ')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PERMISSION_ASSIGN_TO_ROLE')),


    ((select id from roles where name = 'OWNER'), (select id from permissions where name = 'PARKING_CREATE')),
    ((select id from roles where name = 'OWNER'), (select id from permissions where name = 'PARKING_READ')),
    ((select id from roles where name = 'OWNER'), (select id from permissions where name = 'PARKING_UPDATE')),
    ((select id from roles where name = 'OWNER'), (select id from permissions where name = 'PARKING_DISABLE')),

    ((select id from roles where name = 'OWNER'), (select id from permissions where name = 'SPOT_CREATE')),
    ((select id from roles where name = 'OWNER'), (select id from permissions where name = 'SPOT_READ')),
    ((select id from roles where name = 'OWNER'), (select id from permissions where name = 'SPOT_UPDATE')),
    ((select id from roles where name = 'OWNER'), (select id from permissions where name = 'SPOT_DISABLE')),

    ((select id from roles where name = 'OWNER'), (select id from permissions where name = 'RESERVATION_READ')),
    ((select id from roles where name = 'OWNER'), (select id from permissions where name = 'RESERVATION_UPDATE')),
    ((select id from roles where name = 'OWNER'), (select id from permissions where name = 'RESERVATION_CANCEL')),


    ((select id from roles where name = 'OWNER'), (select id from permissions where name = 'PAYMENT_READ')),

    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'PARKING_READ')),


    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'RESERVATION_CREATE')),
    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'RESERVATION_READ')),
    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'RESERVATION_CANCEL')),
    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'RESERVATION_UPDATE')),

    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'PAYMENT_CREATE')),
    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'PAYMENT_READ')),

    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'PROFILE_CREATE')),
    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'PROFILE_READ')),
    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'PROFILE_UPDATE')),
    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'PROFILE_DISABLE'));






