CREATE TABLE library.content_tag
(
    id            SERIAL          NOT NULL,
    tag           VARCHAR(50)     NOT NULL UNIQUE,
    created_date  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date  TIMESTAMP       NULL,

    CONSTRAINT pk__content_tag__id PRIMARY KEY (id)
);