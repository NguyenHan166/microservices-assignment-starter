CREATE DATABASE payment_service_hdv;
CREATE DATABASE booking_service_hdv;
CREATE DATABASE roomseat_service_hdv;

\c payment_service_hdv;
CREATE TABLE IF NOT EXISTS payments (
    id SERIAL PRIMARY KEY,
    amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

\c booking_service_hdv;
CREATE TABLE IF NOT EXISTS bookings (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    booking_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

\c roomseat_service_hdv;
CREATE TABLE IF NOT EXISTS rooms (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS seats (
    id SERIAL PRIMARY KEY,
    room_id BIGINT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    status VARCHAR(50) DEFAULT 'AVAILABLE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
); 