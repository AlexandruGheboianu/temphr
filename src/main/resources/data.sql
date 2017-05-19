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


INSERT INTO SKILLS (name, skill_type, deleted) VALUES
  ('Java', 'BACK_END', 'false'),
  ('PHP', 'BACK_END', 'false'),
  ('HTML', 'FRONT_END', 'false'),
  ('JavaScript', 'FRONT_END', 'false'),
  ('ReactJS', 'FRONT_END', 'false');

INSERT INTO EMPLOYEE_SKILLS (employee_id, skill_id, level, version) VALUES
  (1, 1, 8, 1),
  (1, 5, 6, 1),
  (1, 3, 4, 1),
  (2, 3, 8, 1),
  (2, 4, 7, 1),
  (2, 5, 9, 1),
  (3, 2, 10, 1);



INSERT INTO APP_USER (password, username,first_name,last_name,email,version, created_date) VALUES
('$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G','alexghebo','Alexandru','Gheboinau','alexandru.gheboianu@gmail.com',1,PARSEDATETIME('03-10-2017', 'dd-MM-yyyy')),
('$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G','gigi','Gheorghe','Spoitoru','gspot@example.com',1,PARSEDATETIME('03-10-2017', 'dd-MM-yyyy')),
('$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G','gogu','Gogu','Gogolea','gogoasa@example.com',1,PARSEDATETIME('03-10-2017', 'dd-MM-yyyy'));

INSERT INTO USER_ROLE (role, app_user_id) VALUES
('ADMIN',1),
('MEMBER',3),
('PREMIUM_MEMBER',1);

INSERT INTO CUSTOMERS (name, vat, address, city, country, phone, email, contact_person, bank_account, bank_name, version, deleted) VALUES
('Apples','113415','Raven Avenue 5','New York','U.S.A.','02121212121','apples@gmail.com','Jim Reed','32132131243245','J.P. Morgan',1,'false'),
('Glogoe','213415','SomeStreet 12','Washington','U.S.A.','06216488433','glogoe@gmail.com','Brad Notpitt','32121321325543','City',1,'false'),
('Macrosoft','313415','Some Other Street 22','London','Great Britain','020982272223','macrosoft@gmail.com','Stan Brexitus','8321321454328654','Barclays',1,'false'),
('Paralaxxa','413415','Some Definitely Other Street 35','Marseille','France','03211321546','paralaxxa@gmail.com','Pierre Remainus','6216216543222','Societe Generale',1,'false');

INSERT INTO PROJECTS (name, start_date, version, deleted,customer_id) VALUES
('Continental',PARSEDATETIME('03-10-2017', 'dd-MM-yyyy'),1,'false',1),
('Auchan',PARSEDATETIME('02-02-2017', 'dd-MM-yyyy'),1,'false',1),
('BRD',PARSEDATETIME('01-11-2016', 'dd-MM-yyyy'),1,'false',1),
('Michelin-2017',PARSEDATETIME('01-06-2017', 'dd-MM-yyyy'),1,'false',2),
('Pj1',PARSEDATETIME('01-06-2017', 'dd-MM-yyyy'),1,'false',2);