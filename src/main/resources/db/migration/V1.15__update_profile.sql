alter table profile add column active boolean;
alter table profile add column roles_id int;
alter table profile add constraint fk_profile_role foreign key (roles_id) references roles(id);