
create table
    booking(
                   id int auto_increment primary key,
                   start_date_time DATETIME,
                   end_date_time DATETIME,
                   status varchar(15),
                   car_id int,
                   parkingspot_id int,
                   profile_id int,
                   constraint fk_booking_car foreign key (car_id) references car (id),
                   constraint fk_booking_parkingspot foreign key (parkingspot_id) references parkingspot (id),
                   constraint fk_booking_profile foreign key (profile_id) references profile (id)
    );