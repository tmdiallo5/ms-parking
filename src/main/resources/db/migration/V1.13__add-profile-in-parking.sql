alter table parking add column profile_id int;

alter table parking
    add constraint fk_parking_profile foreign key (profile_id) references profile (id);
