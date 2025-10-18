insert into  parking (`name`, `address_id`)
value ('TMD', (select id from addresses where tag = 'COMPANY' LIMIT 1));

