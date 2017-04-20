INSERT INTO EMPLOYEES (first_name, last_name, email, version) VALUES
  ('Alex', 'Pope', 'apope@example.com', 1),
  ('Mark', 'Smith', 'msmith@example.com', 1),
  ('Daniel', 'Turner', 'dturner@example.com', 1),
  ('Jane', 'Doe', 'jadoe@example.com', 1),
  ('John', 'Doe', 'jdoe@example.com', 1),
  ('Jim', 'Doe', 'jdoe@example.com', 1),
  ('Tim', 'Doe', 'jdoe@example.com', 1),
  ('Dick', 'Doe', 'jdoe@example.com', 1),
  ('Mathew', 'Doe', 'jdoe@example.com', 1),
  ('Dan', 'Doe', 'jdoe@example.com', 1);


INSERT INTO SKILLS (name, skill_type) VALUES
  ('Java', 'BACK_END'),
  ('PHP', 'BACK_END'),
  ('HTML', 'FRONT_END'),
  ('JavaScript', 'FRONT_END'),
  ('ReactJS', 'FRONT_END');

INSERT INTO EMPLOYEE_SKILLS (employee_id, skill_id, level, version) VALUES
  (1, 1, 8, 1),
  (1, 5, 6, 1),
  (1, 3, 4, 1),
  (2, 3, 8, 1),
  (2, 4, 7, 1),
  (2, 5, 9, 1),
  (3, 2, 10, 1);

INSERT INTO APP_USER (password, username) VALUES
('$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G','alexghebo');

INSERT INTO USER_ROLE (role, app_user_id) VALUES
('ADMIN',1),
('PREMIUM_MEMBER',1);