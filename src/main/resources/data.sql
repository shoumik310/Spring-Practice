
insert into user (user_id, email_address, password, username) values (1, 'kiran@gmail.com', '$2A$10$B/KL8DZYRME3AUJQ/DFXWOL3EDFZRKIOKDNMMUQUQU4R4I0CQ/XP2', 'kiran');
insert into user (user_id, email_address, password, username) values (2, 'vinay@gmail.com', '$2A$10$SS2SCLX4CLEWZHQGPCZ4HOG01JIUI7MZRBIAKSYL1VGQDWO6ZKRYE', 'vinay');

insert into role (role_id, role_name) values (1, 'ROLE_USER');
insert into role (role_id, role_name) values (2, 'ROLE_ADMIN');

insert into user_roles(role_id, user_id) values (1, 1),(2, 1), (2,2);