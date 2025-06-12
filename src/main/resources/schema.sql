CREATE TABLE IF NOT EXISTS user (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    country VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS airport (
    airport_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS flight (
    flight_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    flight_number VARCHAR(10) NOT NULL,
    departure_airport_id BIGINT NOT NULL,
    destination_airport_id BIGINT NOT NULL,
    departure_date DATE NOT NULL,
    departure_time TIME NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (departure_airport_id) REFERENCES airport(airport_id),
    FOREIGN KEY (destination_airport_id) REFERENCES airport(airport_id)
);

CREATE TABLE IF NOT EXISTS booking (
    booking_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    flight_id BIGINT NOT NULL,
    reference VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    booking_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (flight_id) REFERENCES flight(flight_id)
);

CREATE TABLE IF NOT EXISTS passenger (
    passenger_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    booking_id BIGINT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES booking(booking_id)
);