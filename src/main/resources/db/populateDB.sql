DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- password
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');
-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES ('ROLE_USER', 100000);
INSERT INTO user_roles (role, user_id) VALUES ('ROLE_ADMIN', 100001);

INSERT INTO meals(date_time, description, calories, user_id) VALUES ('20150310','meal1',100,100000);
INSERT INTO meals(date_time, description, calories, user_id) VALUES ('20150311','meal2',200,100000);
INSERT INTO meals(date_time, description, calories, user_id) VALUES ('20150312','meal3',350,100000);


