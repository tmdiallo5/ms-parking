
create table
    addresses(
        id int auto_increment primary key,
        street varchar(30),
        city varchar(30),
        zip varchar(30),
        country varchar(30),
        creation datetime default current_timestamp
);