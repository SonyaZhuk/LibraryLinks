CREATE TABLE library.user_content_tag
(
    id            SERIAL          NOT NULL,
    user_id       BIGINT          NOT NULL,
    content_id    BIGINT          NOT NULL,
    tag           VARCHAR(50)     NOT NULL,
    created_date  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date  TIMESTAMP       NULL,

    CONSTRAINT pk__user_content_tag__id PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES library.user_lib (id),
    FOREIGN KEY (content_id) REFERENCES library.content (id)
);