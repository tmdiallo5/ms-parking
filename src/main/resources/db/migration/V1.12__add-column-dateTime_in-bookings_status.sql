alter table bookings_status
add column date_time datetime not null  default current_timestamp;
