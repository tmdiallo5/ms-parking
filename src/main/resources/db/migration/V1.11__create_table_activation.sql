create table activations (
    id int primary key auto_increment,
    code varchar(200),
    active boolean,
    creation datetime default current_timestamp,
    desactivation datetime,
    profile_id int,
    constraint fk_activations_profile foreign key (profile_id) references profile(id)
);