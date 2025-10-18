
create table
    car(
           id int auto_increment primary key,
           registration_number  varchar(10) unique not null,
           type varchar(10),
           profile_id int,
           constraint fk_car_profile foreign key (profile_id) references profile (id),
           constraint uq_car_profile unique (registration_number, profile_id)
);