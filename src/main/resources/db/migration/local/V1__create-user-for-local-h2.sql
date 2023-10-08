CREATE TABLE "user"
(
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    user_login VARCHAR(100) NOT NULL,
    user_password VARCHAR(100) NOT NULL
)