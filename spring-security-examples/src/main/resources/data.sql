
insert into user (user_id, email_address, password, username) values (1, 'kiran@gmail.com', '$2a$10$OtuDwYtrJtnAHBvE8qmSDu8cPOP7ulv39eoCdztsGY5ykDNWNWZGe', 'kiran');
insert into user (user_id, email_address, password, username) values (2, 'vinay@gmail.com', '$2a$10$PJm69R7waM9udHFuajfHhOtcg7exPLc.FCNkCMQx0M3WO8.3JNDNC', 'vinay');

insert into role (role_id, role_name) values (1, 'ROLE_USER');
insert into role (role_id, role_name) values (2, 'ROLE_ADMIN');

insert into user_roles(role_id, user_id) values (1, 1),(2, 1), (2,2);