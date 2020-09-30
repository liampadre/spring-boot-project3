-- DELETE FROM clients;
-- DELETE FROM employees;
-- DELETE FROM authorities;
-- DELETE FROM users;

INSERT INTO clients (id, first_name, last_name, email, create_at, status) VALUES (1, 'MIGUEL', 'FLOR', 'mickybasic@gmail.com', '2020-02-20', 'ENABLED');
INSERT INTO clients (id, first_name, last_name, email, create_at, status) VALUES (2, 'JOHANA', 'CACERES', 'jovicafe87@gmail.com', '2020-03-19', 'ENABLED');
INSERT INTO clients (id, first_name, last_name, email, create_at, status) VALUES (3, 'MARIANA', 'FLOR', 'mamifloes@gmail.com', '2014-10-19', 'DISABLED');
INSERT INTO clients (id, first_name, last_name, email, create_at, status) VALUES (4, 'ELENA', 'ESPINOZA', 'helenazvi@gmail.com', '2018-02-11', 'DISABLED');

INSERT INTO employees (id, first_name, last_name, email, age, status) VALUES (1, 'JOAQUIN', 'VELEZ', 'jvelezaranda@gmail.com', 24, 'ACTIVE');
INSERT INTO employees (id, first_name, last_name, email, age, status) VALUES (2, 'PEDRO', 'ANAYA', 'panayap@hotmail.com', 45, 'ACTIVE');
INSERT INTO employees (id, first_name, last_name, email, age, status) VALUES (3, 'MARCOS', 'VELARDE', 'marcos_velarde_costa@hotmail.com', 33, 'FIRED');
INSERT INTO employees (id, first_name, last_name, email, age, status) VALUES (4, 'CARLOS', 'VERA', 'carlosverat@gmail.com', 64, 'ACTIVE');
INSERT INTO employees (id, first_name, last_name, email, age, status) VALUES (5, 'LAURA', 'ESPINOZA', 'lespinozad@gmail.com', 22, 'FIRED');

INSERT INTO users (username, password, enabled) VALUES ('liampadre', '$2a$10$2AbMNQUFYgWatbl3GNNswuNHA.JYU3OhXlTi5ZPRTnitnr7v8Cb/e', 1);
INSERT INTO users (username, password, enabled) VALUES ('jovicafe', '$2a$10$VWkKwq9Z3sZogbsXVJ4IX.86d7s4oMhTgnUu4HzTvaUCvQ.Xbe41S', 1);
INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_CLIENT');
INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_EMPLOYEE');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_CLIENT');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_USER');
