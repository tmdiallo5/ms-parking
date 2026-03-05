create table profile (
     id int auto_increment primary key,
     first_Name varchar(30),
     last_Name varchar(30),
     email varchar(30),
     password varchar(255),
     role_id int,
     constraint fk_profile_role foreign key (role_id) references roles(id)

);