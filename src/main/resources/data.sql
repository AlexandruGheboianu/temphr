INSERT INTO EMPLOYEES (first_name, last_name, email, version,deleted) VALUES
  ('Alex', 'Pope', 'apope@example.com', 1,'false'),
  ('Mark', 'Smith', 'msmith@example.com', 1,'false'),
  ('Daniel', 'Turner', 'dturner@example.com', 1,'false'),
  ('Jane', 'Doe', 'jadoe@example.com', 1,'false'),
  ('John', 'Doe', 'jdoe@example.com', 1,'false'),
  ('Jim', 'Doe', 'jimdoe@example.com', 1,'false'),
  ('Tim', 'Doe', 'tdoe@example.com', 1,'false'),
  ('Dick', 'Doe', 'ddoe@example.com', 1,'false'),
  ('Mathew', 'Doe', 'mdoe@example.com', 1,'false'),
  ('Dan', 'Doe', 'dandoe@example.com', 1,'false');


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

INSERT INTO PROJECTS (name, start_date, version, deleted) VALUES
('Continental',PARSEDATETIME('03-10-2017', 'dd-MM-yyyy'),1,'false'),
('Auchan',PARSEDATETIME('02-02-2017', 'dd-MM-yyyy'),1,'false'),
('BRD',PARSEDATETIME('01-11-2016', 'dd-MM-yyyy'),1,'false'),
('Michelin-2017',PARSEDATETIME('01-06-2017', 'dd-MM-yyyy'),1,'false');

INSERT INTO APP_USER (password, username) VALUES
('$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G','alexghebo'),
('$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G','gigi'),
('$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G','gogu');

INSERT INTO USER_ROLE (role, app_user_id) VALUES
('ADMIN',1),
('MEMBER',3),
('PREMIUM_MEMBER',1);