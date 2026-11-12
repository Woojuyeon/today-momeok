-- 생성자 Oracle SQL Developer Data Modeler 24.3.1.347.1153
--   위치:        2026-11-12 04:15:39 KST
--   사이트:      Oracle Database 12cR2
--   유형:      Oracle Database 12cR2



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE SEQUENCE food_id_sequence START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE SEQUENCE foodlog_id_sequence START WITH 1000 INCREMENT BY 1 NOCACHE;

CREATE SEQUENCE group_id_sequence START WITH 1000 INCREMENT BY 1 NOCACHE;

CREATE TABLE food (
    food_id        INTEGER NOT NULL,
    food_name      VARCHAR2(20) NOT NULL,
    food_category  CHAR(3) NOT NULL,
    food_calories  INTEGER DEFAULT 0,
    food_image     BLOB,
    food_image_ext VARCHAR2(10),
    is_deleted     INTEGER DEFAULT 0 NOT NULL
)
LOGGING;

ALTER TABLE food
    ADD CONSTRAINT food_category_ck
        CHECK ( food_category IN ( 'ASN', 'CHN', 'ETC', 'JPN', 'KOR',
                                   'WES' ) );

ALTER TABLE food
    ADD CONSTRAINT calories_ck CHECK ( food_calories BETWEEN 0 AND 10000 );

ALTER TABLE food
    ADD CONSTRAINT food_is_deleted_ck CHECK ( is_deleted IN ( 0, 1 ) );

CREATE UNIQUE INDEX food_name_active_un ON
    food (
        CASE
            WHEN
                is_deleted
            = 0 THEN
                    food_name
        END
    );

ALTER TABLE food
    ADD CONSTRAINT food_image_ck
        CHECK ( ( food_image IS NULL
                  AND food_image_ext IS NULL )
                OR ( food_image IS NOT NULL
                     AND food_image_ext IS NOT NULL ) );

ALTER TABLE food ADD CONSTRAINT food_pk PRIMARY KEY ( food_id );

CREATE TABLE food_log (
    food_log_id     INTEGER NOT NULL,
    users_user_id   VARCHAR2(10) NOT NULL,
    food_food_id    INTEGER,
    groups_group_id INTEGER,
    eaten_date      DATE NOT NULL
)
LOGGING;

ALTER TABLE food_log ADD CONSTRAINT food_log_pk PRIMARY KEY ( food_log_id );

CREATE TABLE group_guest_member (
    groups_group_guest_id INTEGER NOT NULL,
    is_favorite           INTEGER DEFAULT 0 NOT NULL,
    users_user_id         VARCHAR2(10) NOT NULL,
    guest_id              INTEGER NOT NULL
)
LOGGING;

ALTER TABLE group_guest_member
    ADD CONSTRAINT group_guest_member_is_favorite_ck CHECK ( is_favorite IN ( 0, 1 ) );

ALTER TABLE group_guest_member
    ADD CONSTRAINT group_memberv1_pk PRIMARY KEY ( groups_group_guest_id,
                                                   users_user_id,
                                                   guest_id );

CREATE TABLE group_member (
    groups_group_id   INTEGER NOT NULL,
    is_favorite_group INTEGER DEFAULT 0 NOT NULL,
    users_user_id     VARCHAR2(10) NOT NULL
)
LOGGING;

ALTER TABLE group_member
    ADD CONSTRAINT group_member_is_favorite_ck CHECK ( is_favorite_group IN ( 0, 1 ) );

ALTER TABLE group_member ADD CONSTRAINT group_member_pk PRIMARY KEY ( groups_group_id,
                                                                      users_user_id );

CREATE TABLE groups (
    group_id        INTEGER NOT NULL,
    do_use_food_log INTEGER DEFAULT 0,
    filter_days     INTEGER DEFAULT 0
)
LOGGING;

ALTER TABLE groups
    ADD CONSTRAINT groups_filter_food_log_ck CHECK ( do_use_food_log IN ( 0, 1 ) );

ALTER TABLE groups
    ADD CONSTRAINT groups_filter_days_ck CHECK ( filter_days BETWEEN 0 AND 31 );

ALTER TABLE groups ADD CONSTRAINT groups_pk PRIMARY KEY ( group_id );

CREATE TABLE hate_food (
    user_id         VARCHAR2(10) NOT NULL,
    food_id         INTEGER NOT NULL,
    hate_created_at DATE DEFAULT sysdate
)
LOGGING;

ALTER TABLE hate_food ADD CONSTRAINT hate_food_pk PRIMARY KEY ( user_id,
                                                                food_id );

CREATE TABLE image_ext (
    file_extension VARCHAR2(10) NOT NULL
)
LOGGING;

ALTER TABLE image_ext ADD CONSTRAINT image_ext_pk PRIMARY KEY ( file_extension );

CREATE TABLE like_food (
    user_id         VARCHAR2(10) NOT NULL,
    food_id         INTEGER NOT NULL,
    like_created_at DATE DEFAULT sysdate
)
LOGGING;

ALTER TABLE like_food ADD CONSTRAINT like_food_pk PRIMARY KEY ( user_id,
                                                                food_id );

CREATE TABLE users (
    user_id        VARCHAR2(10) NOT NULL,
    user_pw        VARCHAR2(20) NOT NULL,
    user_name      VARCHAR2(20) NOT NULL,
    user_role      CHAR(1) DEFAULT 'U' NOT NULL,
    user_age       INTEGER,
    user_gender    CHAR(1),
    user_image     BLOB,
    user_image_ext VARCHAR2(10)
)
LOGGING;

ALTER TABLE users
    ADD CONSTRAINT users_role_ck
        CHECK ( user_role IN ( 'A', 'G', 'U' ) );

ALTER TABLE users
    ADD CONSTRAINT users_gender_ck CHECK ( user_gender IN ( 'F', 'M' ) );

ALTER TABLE users
    ADD CONSTRAINT users_image_ck
        CHECK ( ( user_image IS NULL
                  AND user_image_ext IS NULL )
                OR ( user_image IS NOT NULL
                     AND user_image_ext IS NOT NULL ) );

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( user_id );

ALTER TABLE food
    ADD CONSTRAINT food_image_ext_fk
        FOREIGN KEY ( food_image_ext )
            REFERENCES image_ext ( file_extension )
            NOT DEFERRABLE;

ALTER TABLE food_log
    ADD CONSTRAINT food_log_food_fk
        FOREIGN KEY ( food_food_id )
            REFERENCES food ( food_id )
                ON DELETE SET NULL
            NOT DEFERRABLE;

ALTER TABLE food_log
    ADD CONSTRAINT food_log_groups_fk
        FOREIGN KEY ( groups_group_id )
            REFERENCES groups ( group_id )
                ON DELETE SET NULL
            NOT DEFERRABLE;

ALTER TABLE food_log
    ADD CONSTRAINT food_log_users_fk
        FOREIGN KEY ( users_user_id )
            REFERENCES users ( user_id )
            NOT DEFERRABLE;

ALTER TABLE group_member
    ADD CONSTRAINT group_member_groups_fk
        FOREIGN KEY ( groups_group_id )
            REFERENCES groups ( group_id )
                ON DELETE CASCADE
            NOT DEFERRABLE;

ALTER TABLE group_member
    ADD CONSTRAINT group_member_users_fk
        FOREIGN KEY ( users_user_id )
            REFERENCES users ( user_id )
            NOT DEFERRABLE;

ALTER TABLE group_guest_member
    ADD CONSTRAINT group_memberv1_groups_fk
        FOREIGN KEY ( groups_group_guest_id )
            REFERENCES groups ( group_id )
                ON DELETE CASCADE
            NOT DEFERRABLE;

ALTER TABLE group_guest_member
    ADD CONSTRAINT group_memberv1_users_fk
        FOREIGN KEY ( users_user_id )
            REFERENCES users ( user_id )
            NOT DEFERRABLE;

ALTER TABLE hate_food
    ADD CONSTRAINT hate_food_food_fk
        FOREIGN KEY ( food_id )
            REFERENCES food ( food_id )
                ON DELETE CASCADE
            NOT DEFERRABLE;

ALTER TABLE hate_food
    ADD CONSTRAINT hate_food_users_fk
        FOREIGN KEY ( user_id )
            REFERENCES users ( user_id )
            NOT DEFERRABLE;

ALTER TABLE like_food
    ADD CONSTRAINT like_food_food_fk
        FOREIGN KEY ( food_id )
            REFERENCES food ( food_id )
                ON DELETE CASCADE
            NOT DEFERRABLE;

ALTER TABLE like_food
    ADD CONSTRAINT like_food_users_fk
        FOREIGN KEY ( user_id )
            REFERENCES users ( user_id )
            NOT DEFERRABLE;

ALTER TABLE users
    ADD CONSTRAINT users_image_ext_fk
        FOREIGN KEY ( user_image_ext )
            REFERENCES image_ext ( file_extension )
            NOT DEFERRABLE;

CREATE OR REPLACE VIEW v_favorite_group ( users_user_id, groups_group_id, users_user_id_1 ) AS
SELECT me.users_user_id,
       me.groups_group_id,
       m.users_user_id
FROM   group_member me
       JOIN group_member m ON m.groups_group_id = me.groups_group_id
WHERE  me.is_favorite_group = 1
;

CREATE OR REPLACE VIEW v_food_log_detail ( food_log_id, users_user_id, eaten_date, groups_group_id, food_id, food_name ) AS
SELECT l.food_log_id,
       l.users_user_id,
       l.eaten_date,
       l.groups_group_id,
       f.food_id,
       f.food_name
FROM   food_log l
       LEFT JOIN food f ON f.food_id = l.food_food_id 
;

CREATE OR REPLACE VIEW v_group_preference ( group_id, food_id, like_cnt, hate_cnt ) AS
SELECT t.group_id,
       t.food_id,
       SUM(t.like_flag) AS like_cnt,
       SUM(t.hate_flag) AS hate_cnt
FROM   (
         SELECT gm.groups_group_id AS group_id,
                lf.food_id         AS food_id,
                1 AS like_flag,
                0 AS hate_flag
         FROM   group_member gm
                JOIN like_food lf ON lf.user_id = gm.users_user_id
         UNION ALL
         SELECT gm.groups_group_id,
                hf.food_id,
                0,
                1
         FROM   group_member gm
                JOIN hate_food hf ON hf.user_id = gm.users_user_id
       ) t
       JOIN food f   ON f.food_id  = t.food_id  AND f.is_deleted = 0
GROUP  BY t.group_id, t.food_id 
;

CREATE OR REPLACE VIEW v_user_public ( user_id, user_name ) AS
SELECT user_id, user_name
FROM   users 
;



-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                             9
-- CREATE INDEX                             1
-- ALTER TABLE                             33
-- CREATE VIEW                              4
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          3
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
