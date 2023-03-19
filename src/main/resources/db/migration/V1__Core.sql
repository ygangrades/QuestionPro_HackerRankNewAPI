create table story
(
    id bigserial not null
        constraint story_entity_pkey
            primary key,
    title text,
    url varchar(1000),
    score INT,
    time TIMESTAMP,
    by varchar(1000),
    descendants INT
);

