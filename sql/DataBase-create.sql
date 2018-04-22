CREATE TABLE tour (
  tour_id          BIGSERIAL PRIMARY KEY,
  theme            TEXT,
  type_of_exhibits TEXT,
  duration         SMALLINT,
  cost             DOUBLE PRECISION CONSTRAINT positive_price CHECK (cost > 0),
  image_url        TEXT
);

CREATE TABLE guide (
  guide_id   BIGSERIAL PRIMARY KEY,
  fio        TEXT,
  age        SMALLINT CONSTRAINT adult_only CHECK (age > 18),
  experience SMALLINT,
  languages  TEXT,
  tour_id    BIGINT REFERENCES tour (tour_id)
);

CREATE TABLE exhibit (
  exhibit_id  BIGSERIAL PRIMARY KEY,
  title       TEXT,
  dated       TEXT,
  material    TEXT,
  archive_num TEXT UNIQUE,
  description TEXT,
  image_url   TEXT
);

CREATE TABLE tour_exhibit (
  tour_id    INT REFERENCES tour (tour_id),
  exhibit_id INT REFERENCES exhibit (exhibit_id),
  PRIMARY KEY (tour_id, exhibit_id)
);

CREATE TABLE visitor (
  visitor_id BIGSERIAL PRIMARY KEY,
  username   TEXT NOT NULL UNIQUE,
  password   TEXT NOT NULL,
  fio        TEXT,
  age        BIGINT CONSTRAINT adult_only CHECK (age > 0),
  email      TEXT
);

CREATE TABLE tour_visitor (
  tour_id    INT REFERENCES tour (tour_id),
  visitor_id INT REFERENCES visitor (visitor_id),
  PRIMARY KEY (tour_id, visitor_id)
);

DROP TABLE exhibit, guide, tour_exhibit, tour_visitor, tour, visitor;

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





















