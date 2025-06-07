
create table
    parking(
               id int auto_increment primary key,
               name varchar(30),
               capacity int,
               address_id int,
               constraint fk_parking_addresses foreign key (address_id) references addresses (id)
);