-- drop table users;
-- drop table projects cascade;
-- drop table user_project;
-- select * from user_project

create table users (
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	username varchar(255),
	state bool,
	login varchar(255),
	password varchar(255),
    role varchar(255),
	PRIMARY KEY (id)
);

CREATE TABLE projects (
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    projectname varchar(255),
    state bool,
    projectinfo varchar(255),
    projectdata json,
    dt_creation date,
    dt_update date,
    start_coordinates varchar,
    inside_coordinates varchar,
    outside_coordinates varchar,
	PRIMARY KEY (id)
);


CREATE TABLE user_project (
	user_id int NOT NULL,
	project_id int NOT NULL,
	PRIMARY KEY (user_id, project_id),
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (project_id) REFERENCES projects(id)
);

CREATE TABLE user_model (
	user_id int NOT NULL,
	model_id int NOT NULL,
	PRIMARY KEY (user_id, model_id),
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (model_id) REFERENCES models(id)
);

CREATE TABLE tech_econ_performance_normative (
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    project_id int NOT NULL,
    model_id varchar,
    floor_num int,
    aparts_area real,
    comm_area real,
    dou_area real,
    aparts_parking_spot_amount int,
    comm_parking_spot_amount int,
    residents_num real,
    dou_places_num real,
    sou_places_num real,
    total_dou_area real,
    total_playground_area real,
    total_sportground_area real,
    total_recreation_area real,
    total_utility_area real,
    FOREIGN KEY (project_id) REFERENCES projects(id)
);

CREATE TABLE tech_econ_performance_factual (
     id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
     project_id int NOT NULL,
     model_id varchar,
     floor_num int,
     aparts_area real,
     comm_area real,
     dou_area real,
     aparts_parking_spot_amount int,
     comm_parking_spot_amount int,
     residents_num real,
     dou_places_num real,
     sou_places_num real,
     total_dou_area real,
     total_playground_area real,
     total_sportground_area real,
     total_recreation_area real,
     total_utility_area real,
     FOREIGN KEY (project_id) REFERENCES projects(id)
);

CREATE TABLE object_type (
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    object_type varchar,
    expect_subtype bool,
    state bool
);

CREATE TABLE object_subtype (
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    object_subtype varchar,
    state bool
);

insert into object_type (object_type, expect_subtype, state) VALUES
    ('Жилое', false, true),
    ('Общественное', false, true),
    ('Коммерческое', false, true),
    ('Паркинг', false, true),
    ('Площадка', true, true);

insert into object_subtype (object_subtype, state) VALUES
    ('Спортивная площадка', true),
    ('Постоянные машино-места', true),
    ('Временные машино-места', true),
    ('ДОУ', true),
    ('Детская площадка', true),
    ('Площадка отдыха', true),
    ('Хозяйственная площадка', true);

