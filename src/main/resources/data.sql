INSERT INTO users (username, EMAIL, password)
VALUES ('David', 'david@mail.co', '$2a$10$.yQE2RZ/r5ZnYnoV3g7C5O2TdEX55H2cuoTzAes65MoN0TI1vhGKK'),
       ('Medina', 'medina@mail.co', '$2a$10$.yQE2RZ/r5ZnYnoV3g7C5O2TdEX55H2cuoTzAes65MoN0TI1vhGKK'),
       ('Luis', 'luis@mail.co', '$2a$10$.yQE2RZ/r5ZnYnoV3g7C5O2TdEX55H2cuoTzAes65MoN0TI1vhGKK');

INSERT INTO roles (NAME)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO user_roles (USER_ID, ROLE_ID)
VALUES (1, 1),
       (2, 2),
       (3, 2);

INSERT INTO room (NAME, LOCATION, CAPACITY, START_TIME, FINISH_TIME, IS_ACTIVE)
VALUES ('Room 1', 'Medellín', 3, '07:30', '17:30', TRUE),
       ('Room 2', 'Bogotá', 3, '06:20', '18:50', TRUE),
       ('Room 3', 'Marinilla', 3, '09:45', '20:56', TRUE);


INSERT INTO booking (DATE, END_TIME, START_TIME, ROOM_ID, USER_ID)
VALUES ('2023-07-31', '12:40', '08:54', 1, 1),
       ('2023-07-31', '12:42', '12:40', 1, 1),
       ('2023-07-31', '12:43', '12:42', 1, 1),
       ('2023-07-31', '14:00', '13:00', 1, 1),
       ('2023-07-31', '14:20', '15:00', 1, 1),
       ('2023-07-28', '10:00', '09:00', 2, 2),
       ('2023-07-28', '11:24', '10:16', 2, 2);

INSERT INTO booking (DATE, END_TIME, START_TIME, ROOM_ID, USER_ID)
VALUES ('2023-07-28', '14:00', '09:16', 2, 2),
       ('2023-07-28', '14:24', '15:16', 2, 2);
