CREATE TABLE library.user_content
(
    id             SERIAL          NOT NULL,
    user_id        BIGINT          NOT NULL,
    content_tag_id BIGINT          NOT NULL,
    link           VARCHAR(255)    NOT NULL UNIQUE,
    type           VARCHAR(50)     NOT NULL,
    name           VARCHAR(100)    NOT NULL,
    description    VARCHAR(255)    NULL,
    priority       VARCHAR(20)     NOT NULL,
    status         VARCHAR(30)     NOT NULL,
    rating         INT             NULL,
    created_date   TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date   TIMESTAMP       NULL,

    CONSTRAINT pk__user_content__id PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES library.user_lib (id),
    FOREIGN KEY (content_tag_id) REFERENCES library.content_tag (id)
);