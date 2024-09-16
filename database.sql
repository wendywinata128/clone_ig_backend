CREATE DATABASE mysql_commsult_ig;

USE mysql_commsult_ig;

DELETE FROM users;

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    username VARCHAR(100),
    name VARCHAR(100),
    avatar VARCHAR(100) NULL, 
    createddt TIMESTAMP,
    updateddt TIMESTAMP
);

CREATE TABLE posts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    photo_url VARCHAR(100),
    description TEXT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    createddt TIMESTAMP,
    updateddt TIMESTAMP
)

CREATE TABLE comments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description TEXT,
    user_id BIGINT,
    post_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (post_id) REFERENCES posts(id),
    createddt TIMESTAMP,
    updateddt TIMESTAMP
);


CREATE TABLE post_likes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    post_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (post_id) REFERENCES posts(id),
    createddt TIMESTAMP,
    updateddt TIMESTAMP
);

CREATE TABLE user_follows (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    follow_user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (follow_user_id) REFERENCES users(id),
    createddt TIMESTAMP,
    updateddt TIMESTAMP
)



