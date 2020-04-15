
insert into user (user_id, email_address, password, username) values (1, 'kiran@gmail.com', '$2A$10$EYQ2LNWTBRKXDJG7CPFOX.KUW4I3QIJEU.ZHME3CAIWCK1U0UHIRM', 'kiran');
insert into user (user_id, email_address, password, username) values (2, 'vinay@gmail.com', '$2A$10$TN.AT/ISGMOASVWTK0SMOUEGMSEBUKLAJHHD2/GOVJYXV.6NY3KRQ', 'vinay');

insert into role (role_id, role_name) values (1, 'ROLE_USER');
insert into role (role_id, role_name) values (2, 'ROLE_ADMIN');

insert into user_roles(role_id, user_id) values (1, 1),(2, 1), (2,2);