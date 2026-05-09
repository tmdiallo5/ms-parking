create table parking(
    id int primary key auto_increment,
    name varchar(100),
    description varchar(200),
    parking_status varchar(100),
    price_per_hour double,
    latitude double,
    longitude double,
    image_url varchar(200),
    address_id int,
    constraint fk_address_parking foreign key (address_id) references address(id)

);


create table spot(
     id int primary key auto_increment,
     number varchar(20),
     spot_status varchar(100),
     spot_type varchar(100),
     parking_id int,
     constraint fk_parking_spot foreign key (parking_id) references parking(id)

);

insert into parking(
    name,
    description,
    parking_status,
    price_per_hour,
    latitude,
    longitude,
    address_id
)
values
    (
        'Bremen Central Parking',
        'Parking near Bremen central station',
        'OPEN',
        3.5,
        53.0826,
        8.8136,
        1
    ),
    (
        'University Parking',
        'Parking near the university',
        'OPEN',
        2.0,
        53.1066,
        8.8505,
        2
    ),
    (
        'Airport Parking',
        'Parking near Bremen airport',
        'OPEN',
        4.0,
        53.0475,
        8.7867,
        3
    ),
    (
        'Waterfront Parking',
        'Parking near Waterfront shopping center',
        'OPEN',
        2.5,
        53.1158,
        8.7574,
        4
    ),
    (
        'City Mall Parking',
        'Parking near the city shopping mall',
        'OPEN',
        3.0,
        53.0793,
        8.8017,
        5
    );

insert into spot(
    number,
    spot_status,
    spot_type,
    parking_id
)
values

-- Bremen Central Parking
('A1', 'AVAILABLE', 'STANDARD', 1),
('A2', 'AVAILABLE', 'HANDICAPPED', 1),
('A3', 'AVAILABLE', 'STANDARD', 1),
('A4', 'AVAILABLE', 'HANDICAPPED', 1),

-- University Parking
('B1', 'AVAILABLE', 'STANDARD', 2),
('B2', 'AVAILABLE', 'HANDICAPPED', 2),
('B3', 'AVAILABLE', 'STANDARD', 2),
('B4', 'AVAILABLE', 'HANDICAPPED', 2),

-- Airport Parking
('C1', 'AVAILABLE', 'STANDARD', 3),
('C2', 'AVAILABLE', 'HANDICAPPED', 3),
('C3', 'AVAILABLE', 'STANDARD', 3),
('C4', 'AVAILABLE', 'HANDICAPPED', 3),

-- Waterfront Parking
('D1', 'AVAILABLE', 'STANDARD', 4),
('D2', 'AVAILABLE', 'HANDICAPPED', 4),
('D3', 'AVAILABLE', 'STANDARD', 4),
('D4', 'AVAILABLE', 'HANDICAPPED', 4),

-- City Mall Parking
('E1', 'AVAILABLE', 'STANDARD', 5),
('E2', 'AVAILABLE', 'HANDICAPPED', 5),
('E3', 'AVAILABLE', 'STANDARD', 5),
('E4', 'AVAILABLE', 'HANDICAPPED', 5);