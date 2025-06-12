ALTER TABLE booking
ADD COLUMN client_id INT,
ADD CONSTRAINT fk_booking_client FOREIGN KEY (client_id) REFERENCES client(id);
