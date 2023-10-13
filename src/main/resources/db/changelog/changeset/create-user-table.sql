CREATE TABLE library.user_lib
(
    id           SERIAL       NOT NULL,
    login        VARCHAR(20)  NOT NULL UNIQUE,
    password     VARCHAR(20)  NOT NULL,
    name         VARCHAR(50)  NOT NULL,
    surname      VARCHAR(50)  NOT NULL,
    email        VARCHAR(50)  NOT NULL UNIQUE,
    created_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP    NULL,

    CONSTRAINT pk__user_lib__id PRIMARY KEY (id)
);