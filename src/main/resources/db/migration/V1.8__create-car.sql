
create table
    car(
           id int auto_increment primary key,
           immatriculation varchar(10),
           type varchar(10),
           client_id int,
           constraint fk_car_client foreign key (client_id) references client (id)
);