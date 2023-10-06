CREATE TABLE "user" (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_name VARCHAR(500),
    email VARCHAR(1000),
    login VARCHAR(1000),
    password VARCHAR(100) CHECK (LENGTH(password) >= 8 AND LENGTH(password) <= 100)
);

CREATE TABLE Url (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    longUrl VARCHAR(100000),
    createdAt DATE,
    clickTimes INTEGER
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES user (id)
);