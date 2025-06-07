INSERT INTO parking (`name`,`capacity`, `address_id`)
VALUES
    ('do ad est',28, (select id from addresses where tag = 'COMPANY' LIMIT 1)),
    ('irure culpa Lorem',32, (select id from addresses where tag = 'COMPANY' LIMIT 1)),
    ('irure magna esse',34, (select id from addresses where tag = 'COMPANY' LIMIT 1)),
    ('magna veniam labore',31, (select id from addresses where tag = 'COMPANY' LIMIT 1)),
    ('excepteur magna do',31, (select id from addresses where tag = 'COMPANY' LIMIT 1)),
    ('dolor aliqua quis',39, (select id from addresses where tag = 'COMPANY' LIMIT 1));