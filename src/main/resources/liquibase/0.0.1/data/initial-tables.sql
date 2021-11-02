create table padel_app_db.tbl_role
(
    id bigint auto_increment primary key,
    name varchar(255) null
) charset = utf8;

create table padel_app_db.tbl_user
(
    id bigint auto_increment primary key,
    firstName varchar(255) null,
    lastName varchar(255) null,
    email varchar(255) null,
    password varchar(255) null,
    phoneNumber bigint,
    username varchar(255) not null,
    constraint UK_k0bty7tbcye41jpxam88q5kj2 unique (username)
) charset = utf8;

create table padel_app_db.tbl_user_role_rel
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FKf4vv7ck54h6eri2u99w8nl44v foreign key (user_id) references tbl_user (id),
    constraint FKikaqh87rps27khoky1rew5tef foreign key (role_id) references tbl_role (id)
) charset = utf8;

create table padel_app_db.tbl_game
(
    id bigint auto_increment primary key,
    address varchar(255) null,
    comments varchar(255) null,
    date datetime null,
    status int null,
    time datetime null
) charset = utf8;


