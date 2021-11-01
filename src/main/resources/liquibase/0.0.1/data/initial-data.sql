insert into tbl_role (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into tbl_user (firstName, lastName, email, username, password, phoneNumber)
values ('John', 'Dear','john@gmail.com', 'john', '$2a$10$lZwIaeIQlRjkdxaMp1q1TusAFcor2o0llPm4h3z6LvrtIGcSOzuW6', '999999999'),
       ('Meghan', 'Fox', 'meghan@gmail.com', 'meghan', '$2a$10$lZwIaeIQlRjkdxaMp1q1TusAFcor2o0llPm4h3z6LvrtIGcSOzuW6','888888888');

insert into tbl_user_role_rel (user_id, role_id)
values (1, 1),
       (1, 2),
       (2, 1);

