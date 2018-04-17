CREATE TABLE tour (
  tour_id          BIGSERIAL PRIMARY KEY,
  theme            TEXT,
  type_of_exhibits TEXT,
  duration         SMALLINT,
  cost             DOUBLE PRECISION CONSTRAINT positive_price CHECK (cost > 0)
);

CREATE TABLE guide (
  guide_id   BIGSERIAL PRIMARY KEY,
  fio        TEXT,
  age        SMALLINT CONSTRAINT adult_only CHECK (age > 18),
  experience SMALLINT,
  languages  TEXT,
  tour_id    BIGINT UNIQUE REFERENCES tour (tour_id)
);

CREATE TABLE exhibit (
  exhibit_id  BIGSERIAL PRIMARY KEY,
  title       TEXT,
  dated       DATE,
  material    TEXT,
  archive_num TEXT UNIQUE,
  description TEXT
);

CREATE TABLE tour_exhibit (
  tour_id    INT REFERENCES tour (tour_id),
  exhibit_id INT REFERENCES exhibit (exhibit_id),
  PRIMARY KEY (tour_id, exhibit_id)
);

CREATE TABLE visitor (
  visitor_id BIGSERIAL PRIMARY KEY,
  login      TEXT,
  fio        TEXT,
  age        BIGINT CONSTRAINT adult_only CHECK (age > 0),
  email      TEXT
);

CREATE TABLE tour_visitor (
  tour_id    INT REFERENCES tour (tour_id),
  visitor_id INT REFERENCES visitor (visitor_id),
  PRIMARY KEY (tour_id, visitor_id)
);


<==============================================================>

DROP TABLE exhibit, guide, tour_exhibit, tour_visitor, tour, visitor

<==============================================================>


CREATE TABLE users (
  user_id   BIGSERIAL PRIMARY KEY,
  user_name TEXT    NOT NULL UNIQUE,
  password  TEXT    NOT NULL,
  enabled   BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE SEQUENCE user_roles_seq;

CREATE TABLE user_roles (
  user_role_id BIGINT      NOT NULL DEFAULT NEXTVAL('user_roles_seq'),
  user_name    VARCHAR(45) NOT NULL,
  role         VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  CONSTRAINT uni_username_role UNIQUE (role, user_name)
  ,
  CONSTRAINT fk_username FOREIGN KEY (user_name) REFERENCES users (user_name)
);

CREATE INDEX fk_username_idx
  ON user_roles (user_name);

oRRRRRRRRRRRRRRRRRR

CREATE TABLE user_roles (
  user_role_id BIGSERIAL   NOT NULL,
  user_name    VARCHAR(45) NOT NULL,
  role         VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  CONSTRAINT uni_username_role UNIQUE (role, user_name)
  ,
  CONSTRAINT fk_username FOREIGN KEY (user_name) REFERENCES users (user_name)
);


<===========================================================>

JWT

CREATE TABLE users (
  id       BIGSERIAL PRIMARY KEY,
  username TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL
);

CREATE TABLE roles (
  id   BIGSERIAL PRIMARY KEY,
  name TEXT NOT NULL UNIQUE
);

CREATE TABLE users_roles (
  id      BIGSERIAL PRIMARY KEY,
  user_id BIGINT REFERENCES users (id),
  role_id BIGINT REFERENCES roles (id),
  CONSTRAINT uni_user_role UNIQUE (user_id, role_id)
);





















