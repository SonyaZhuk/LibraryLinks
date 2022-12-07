CREATE TABLE library.content
(
    id            SERIAL        NOT NULL,
    link          VARCHAR(255)  NOT NULL,
    type          VARCHAR(50)   NOT NULL,
    created_date  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date  TIMESTAMP     NULL,

    CONSTRAINT pk__content__id PRIMARY KEY (id)
);