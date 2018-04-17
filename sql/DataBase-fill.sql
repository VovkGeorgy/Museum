
INSERT INTO tour (theme, type_of_exhibits, duration, cost) VALUES
  ('Italian Renaissance', 'paintings', '1 hours 30 minutes', 300),
  ('The Art of Eating','paintings', '2 hours 35 minutes', 200),
  ('On Horseback through the Louvre', 'paintings', '1 hours 5 minutes', 250);
  
INSERT INTO guide (fio, age, experience, languages, tour_id) VALUES
  ('Guide 1 1', 21, 2, 'EN,FR', 1),
  ('Guide 2 2', 22, 3, 'EN,FR,PL', 2),
  ('Guide 3 3', 23, 4, 'EN,RU,CHN', 3);


<==========================================================>

<==========================================================>


INSERT INTO car (mark, reg_number, mileage)
VALUES ('Daewoo', '2003-KA-4', 222000),
  ('Chevrolet', '3742-KB-4', 120000),
  ('Daewoo', '3722-KB-4', 10000),
  ('Kia', '2432-KB-4', 40000),
  ('Vaz', '4333-KB-4', NULL),
  ('Volkswagen', '2200-KB-4', 158000);

INSERT INTO teacher (fio, experience, students_number, car_id)
VALUES ('Zognin A.E.', 20, 22, 1),
  ('Shved D.G.', 12, 21, 2),
  ('Kurmash D.C.', 3, NULL, NULL),
  ('Ulianov V.I.', 22, 18, 3);

INSERT INTO exam (exam_type, exam_topic, ticket_number)
VALUES ('Credit', 'Topic_1', 20),
  ('Credit', 'Topic_2', 20),
  ('Credit', 'Topic_3', 10),
  ('Exam', 'Pre_Final', 30),
  ('Exam', 'Final', 30);

INSERT INTO student (fio, work_group, years_old, teacher_id)
VALUES ('Vovk G.A.', 21, 22, 2),
  ('Ignatenko V.A.', 11, 24, 3),
  ('Trafim O.E.', 44, 34, 1),
  ('Gorbach E.T.', 22, 25, 3),
  ('Surmach D.D.', 21, 33, 3),
  ('Hudoy O.O.', NULL, 23, NULL),
  ('Poveday C.V.', 11, 31, 2);
  

INSERT INTO result_of_exam (student_id, exam_id, passing)
VALUES (1, 2, TRUE),
  (1, 1, TRUE),
  (1, 3, NULL),
  (2, 1, FALSE),
  (2, 2, FALSE),
  (3, 1, TRUE),
  (3, 2, TRUE),
  (3, 3, FALSE);
			

INSERT INTO users(username,password,enabled)
VALUES ('admin','11111', true);
INSERT INTO users(username,password,enabled)
VALUES ('user','11111', true);

INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('user', 'ROLE_USER');