create table demo.account
(
	id bigint auto_increment
		primary key,
	username varchar(48) not null,
	password varchar(128) null,
	status smallint(4) default 0 null comment '状态',
	type smallint(4) default 0 null comment '类型',
	nickname varchar(48) default '小明' null comment '昵称',
	email varchar(128) null comment '邮箱',
	mobile varchar(12) null comment '手机',
	department varchar(48) null comment '部门',
	create_time datetime default CURRENT_TIMESTAMP null,
	modify_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
	is_deleted smallint(1) null,
	constraint account_username_uindex
		unique (username)
);

create table demo.account_role
(
	id bigint auto_increment
		primary key,
	account_id bigint null,
	role_id bigint null
);

create table demo.authority
(
	id bigint auto_increment
		primary key,
	type int null,
	pid bigint null,
	authority varchar(32) null,
	authority_name varchar(32) null,
	create_time datetime default CURRENT_TIMESTAMP null,
	modify_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
	is_deleted smallint(1) null
);

create table demo.role
(
	id bigint auto_increment
		primary key,
	role varchar(32) null,
	role_name varchar(128) null,
	create_time datetime default CURRENT_TIMESTAMP null,
	modify_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
	is_deleted smallint(1) null
);

create table demo.role_authority
(
	id bigint auto_increment
		primary key,
	role_id bigint null,
	authority_id bigint null
);

