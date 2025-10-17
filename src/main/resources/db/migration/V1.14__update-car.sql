alter table car
add constraint uq_car_profile unique (registration_number, profile_id);