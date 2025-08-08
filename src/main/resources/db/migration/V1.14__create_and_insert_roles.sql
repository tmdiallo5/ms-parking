create table roles
(
    id int auto_increment primary key,
    name varchar(255) not null ,
    description varchar(255),
    creation datetime default current_timestamp

);

insert into roles(name)
values ('ADMINISTRATOR'),
       ('CLIENT');

create table permissions
(
    id int auto_increment primary key,
    name varchar(255) not null,
    description varchar(255),
    creation datetime default current_timestamp

);

insert into permissions(name)
values

       ('BOOKING_CREATE'),
       ('BOOKING_READ'),
       ('BOOKING_UPDATE'),
       ('BOOKING_DELETE'),

       ('PARKINGSPOT_CREATE'),
       ('PARKINGSPOT_READ'),
       ('PARKINGSPOT_UPDATE'),
       ('PARKINGSPOT_DELETE');


create table roles_permissions
(
    id int auto_increment primary key,
    role_id int,
    permission_id int,
    creation datetime default current_timestamp,
    constraint fk_role_permission_id foreign key (role_id) references roles(id),
    constraint fk_permission_role_id foreign key (permission_id) references permissions(id),
    constraint unique_role_permission unique(role_id, permission_id)


);

insert into roles_permissions(role_id, permission_id)
values
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'BOOKING_CREATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'BOOKING_READ')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'BOOKING_UPDATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'BOOKING_DELETE')),


    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PARKINGSPOT_CREATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PARKINGSPOT_READ')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PARKINGSPOT_UPDATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PARKINGSPOT_DELETE')),


    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'BOOKING_CREATE')),
    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'BOOKING_READ')),
    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'BOOKING_UPDATE')),
    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'BOOKING_DELETE')),

    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'PARKINGSPOT_READ'));



