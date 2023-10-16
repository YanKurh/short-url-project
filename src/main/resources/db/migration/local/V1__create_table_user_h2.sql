CREATE TABLE "user"
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(200),
    last_name  VARCHAR(200),
    email      VARCHAR(1000),
    user_name      VARCHAR(1000),
    password   VARCHAR(100) CHECK (LENGTH(password) >= 8 AND LENGTH(password) <= 100),
    confirm_password   VARCHAR(100) CHECK (LENGTH(password) >= 8 AND LENGTH(password) <= 100),
    role varchar(255) not null
);

CREATE TABLE url
(
    id              INTEGER PRIMARY KEY AUTO_INCREMENT,
    long_url        VARCHAR(100000),
    short_url       VARCHAR(50),
    created_at      DATE,
    click_times     INTEGER,
    expiration_date DATE,
    user_id         INTEGER,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);