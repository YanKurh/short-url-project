CREATE TABLE "users"
(
    id        INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_name VARCHAR(500),
    email     VARCHAR(1000)                                                          NOT NULL,
    login     VARCHAR(1000)                                                          NOT NULL,
    password  VARCHAR(100) CHECK (LENGTH(password) >= 8 AND LENGTH(password) <= 100) NOT NULL
);

CREATE TABLE "urls"
(
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    long_url        VARCHAR(100000),
    short_url       VARCHAR(50),
    created_at      DATE,
    click_times     INTEGER,
    expiration_date DATE,
    user_id         INTEGER,
    FOREIGN KEY (user_id) REFERENCES "users" (id)
);