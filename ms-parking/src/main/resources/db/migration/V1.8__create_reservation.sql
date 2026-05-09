create table reservation(
    id int primary key auto_increment,
    start_date_Time datetime,
    end_date_Time datetime,
    reservation_status varchar(100),
    profile_id int,
    spot_id int,
    constraint fk_profile_reservation foreign key (profile_id) references profile(id),
    constraint fk_spot_reservation foreign key (spot_id) references spot(id)

);


