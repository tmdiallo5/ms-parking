
create table
    booking(
                   id int auto_increment primary key,
                   startDateTime DATETIME,
                   endDateTime DATETIME,
                   status varchar(15),
                   car_id int,
                   parkingspot_id int,
                   constraint fk_booking_car foreign key (car_id) references car (id),
                   constraint fk_booking_parkingspot foreign key (parkingspot_id) references parkingspot (id)
);