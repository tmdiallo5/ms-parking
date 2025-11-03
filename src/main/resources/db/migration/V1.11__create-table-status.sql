create table status (
     id int auto_increment primary key not null,
     label varchar(10)
);

insert into status(label)
values
    ('BOOKED'),
    ('EXPIRED');

create table bookings_status(
      id int auto_increment primary key not null,
      booking_id int,
      status_id int,
      constraint fk_booking_status foreign key (booking_id) references booking(id),
      constraint fk_status_booking foreign key (status_id) references status(id)

);
