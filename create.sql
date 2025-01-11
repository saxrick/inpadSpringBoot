-- drop table users;
-- drop table projects cascade;
-- drop table user_project;
-- select * from user_project

-- create table users (
-- 	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
-- 	username varchar(255),
-- 	state bool,
-- 	login varchar(255),
-- 	password varchar(255),
--     role varchar(255),
-- 	PRIMARY KEY (id)
-- );
--
-- CREATE TABLE projects (
-- 	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
-- 	projectname varchar(255),
-- 	state bool,
--     projectinfo varchar(255),
-- 	PRIMARY KEY (id)
-- );
--
-- CREATE TABLE models (
--     id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
--     modelname varchar(255),
--     state bool,
--     modelinfo varchar(255),
--     PRIMARY KEY (id)
-- );
--
-- CREATE TABLE user_project (
-- 	user_id int NOT NULL,
-- 	project_id int NOT NULL,
-- 	PRIMARY KEY (user_id, project_id),
-- 	FOREIGN KEY (user_id) REFERENCES users(id),
-- 	FOREIGN KEY (project_id) REFERENCES projects(id)
-- );

CREATE TABLE user_model (
	user_id int NOT NULL,
	model_id int NOT NULL,
	PRIMARY KEY (user_id, model_id),
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (model_id) REFERENCES models(id)
);