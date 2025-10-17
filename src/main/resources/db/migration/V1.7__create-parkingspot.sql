
create table
    parkingspot(
                   id int auto_increment primary key,
                   number varchar(10),
                   type varchar(30),
                   parking_id int,
                   constraint fk_parkingspot_parking foreign key (parking_id) references parking (id),
                   constraint uq_parking_number unique (parking_id, number)

);