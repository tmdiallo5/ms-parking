insert into roles(name)
value ('PARKING_MANAGER');

insert into permissions(name)
values
    ('USER_CREATE'),
    ('USER_READ'),
    ('USER_UPDATE'),
    ('USER_DELETE'),


    ('ROLE_CREATE'),
    ('ROLE_READ'),
    ('ROLE_UPDATE'),
    ('ROLE_DELETE'),


    ('PARKING_CREATE'),
    ('PARKING_READ'),
    ('PARKING_UPDATE'),
    ('PARKING_DELETE');

insert into roles_permissions(role_id, permission_id)
values


    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PARKING_CREATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PARKING_READ')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PARKING_UPDATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'PARKING_DELETE')),

    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'ROLE_CREATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'ROLE_READ')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'ROLE_UPDATE')),
    ((select id from roles where name = 'ADMINISTRATOR'), (select id from permissions where name = 'ROLE_DELETE')),

    ((select id from roles where name = 'PARKING_MANAGER'), (select id from permissions where name = 'PARKING_CREATE')),
    ((select id from roles where name = 'PARKING_MANAGER'), (select id from permissions where name = 'PARKING_READ')),
    ((select id from roles where name = 'PARKING_MANAGER'), (select id from permissions where name = 'PARKING_UPDATE')),
    ((select id from roles where name = 'PARKING_MANAGER'), (select id from permissions where name = 'PARKING_DELETE')),


    ((select id from roles where name = 'PARKING_MANAGER'), (select id from permissions where name = 'PARKINGSPOT_CREATE')),
    ((select id from roles where name = 'PARKING_MANAGER'), (select id from permissions where name = 'PARKINGSPOT_READ')),
    ((select id from roles where name = 'PARKING_MANAGER'), (select id from permissions where name = 'PARKINGSPOT_UPDATE')),
    ((select id from roles where name = 'PARKING_MANAGER'), (select id from permissions where name = 'PARKINGSPOT_DELETE')),


    ((select id from roles where name = 'PARKING_MANAGER'), (select id from permissions where name = 'BOOKING_CREATE')),
    ((select id from roles where name = 'PARKING_MANAGER'), (select id from permissions where name = 'BOOKING_READ')),
    ((select id from roles where name = 'PARKING_MANAGER'), (select id from permissions where name = 'BOOKING_UPDATE')),
    ((select id from roles where name = 'PARKING_MANAGER'), (select id from permissions where name = 'BOOKING_DELETE')),


    ((select id from roles where name = 'CLIENT'), (select id from permissions where name = 'PARKING_READ'));





