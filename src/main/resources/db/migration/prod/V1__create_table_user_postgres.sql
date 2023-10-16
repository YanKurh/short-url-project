CREATE TABLE "user"
(
    id               INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name       VARCHAR(200),
    last_name        VARCHAR(200),
    email            VARCHAR(1000),
    login            VARCHAR(1000),
    password         VARCHAR(100) CHECK (LENGTH(password) >= 8 AND LENGTH(password) <= 100),
    confirm_password VARCHAR(100) CHECK ( confirm_password = password )
);

CREATE TABLE url
(
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    long_URL        VARCHAR(100000),
    short_URL       VARCHAR(50),
    created_at      TIMESTAMP,
    click_times     INTEGER,
    expiration_date TIMESTAMP,
    user_id         INTEGER,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);