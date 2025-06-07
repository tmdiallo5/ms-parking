
create table
    client(
              id int auto_increment primary key,
              first_Name varchar(30),
              last_Name varchar(30),
              email varchar(30),
              password varchar(30),
              address_id int,
              constraint fk_client_addresses foreign key (address_id) references addresses (id)
);