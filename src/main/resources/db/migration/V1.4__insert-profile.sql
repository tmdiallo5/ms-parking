INSERT INTO profile (`first_Name`, `last_Name`, `email`, `password`, `address_id`)
VALUES
    ('cillum', 'quis', 'sybilmartinez@artworlds.com', 'skjdblzvgh', (select address_id from addresses where tag = 'CITY_HALL' LIMIT 1)),
   ('duis', 'quis', 'amdomartinez@artworlds.com', 'owhjnczwzkc', (select address_id from addresses where tag = 'CITY_HALL' LIMIT 1)),
   ('nisi', 'tempor', 'ptersrtinez@artworlds.com', 'abfhnmso', (select address_id from addresses where tag = 'CITY_HALL' LIMIT 1)),
   ('id', 'reprehenderit', 'lotrtinez@artworlds.com', 'opkcown',(select address_id from addresses where tag = 'CITY_HALL' LIMIT 1)),
   ('ex', 'excepteur', 'pwsdartinez@artworlds.com', 'pajishnd', (select address_id from addresses where tag = 'CITY_HALL' LIMIT 1)),
   ('ullamco', 'dolor', 'sjhfartinez@artworlds.com', 'paojfnj', (select address_id from addresses where tag = 'CITY_HALL' LIMIT 1));