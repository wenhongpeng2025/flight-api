INSERT INTO user (email, password, first_name, last_name, country, phone) 
VALUES 
    ('alice@example.com', 'encrypted_password_123', 'Alice', 'Johnson', 'United States', '+1234567890'),
    ('bob@example.com', 'secure_pass_456', 'Bob', 'Smith', 'Canada', '+1987654321');

INSERT INTO airport (code, name, city) 
VALUES 
    ('JFK', 'John F. Kennedy International Airport', 'New York'),
    ('LAX', 'Los Angeles International Airport', 'Los Angeles'),
    ('LHR', 'Heathrow Airport', 'London');

INSERT INTO flight (flight_number, departure_airport_id, destination_airport_id, departure_date, departure_time, price)
VALUES 
    ('FL123', 1, 2, '2023-11-15', '08:30:00', 299.99),
    ('FL456', 2, 3, '2023-11-16', '15:45:00', 499.50);

INSERT INTO booking (user_id, flight_id, reference, status, booking_time, total_price) 
VALUES 
    (1, 1, 'BOOK-REF-001', 'CONFIRMED', '2023-10-01 10:00:00', 599.99),
    (2, 2, 'BOOK-REF-002', 'PENDING', '2023-10-02 14:30:00', 899.50);

INSERT INTO passenger (booking_id, first_name, last_name, email) 
VALUES 
    (1, 'Emma', 'Watson', 'emma@example.com'),
    (1, 'Daniel', 'Radcliffe', 'daniel@example.com'),
    (2, 'Tom', 'Hanks', 'tom@example.com');