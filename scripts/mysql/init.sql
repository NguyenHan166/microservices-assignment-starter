CREATE DATABASE IF NOT EXISTS user_service_hdv;
CREATE DATABASE IF NOT EXISTS movie_service_hdv;

USE user_service_hdv;
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

USE movie_service_hdv;
CREATE TABLE IF NOT EXISTS movies (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    duration INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
); 