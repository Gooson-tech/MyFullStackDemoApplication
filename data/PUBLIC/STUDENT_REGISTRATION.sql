create table STUDENT_REGISTRATION
(
    ID         BIGINT                 not null
        primary key,
    COURSE     CHARACTER VARYING(255),
    EMAIL      CHARACTER VARYING(255),
    FIRST_NAME CHARACTER VARYING(255) not null,
    LAST_NAME  CHARACTER VARYING(255) not null,
    NOTE       CHARACTER VARYING(400),
    SEMESTER   CHARACTER VARYING(255)
);
