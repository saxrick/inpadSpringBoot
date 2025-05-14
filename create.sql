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

CREATE TABLE object_type (
     id int NOT NULL GENERATED ALWAYS AS IDENTITY,
     object_type varchar,
     expect_subtype bool,
     state bool,
     PRIMARY KEY (id)
);

CREATE TABLE object_subtype (
    id int NOT NULL GENERATED ALWAYS AS IDENTITY,
    object_subtype varchar,
    state bool,
    PRIMARY KEY (id)
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

CREATE TABLE tech_econ_performance_normative (
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    object_type bigint,
    object_subtype bigint,
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
    total_util_area real,
    PRIMARY KEY (id),
    FOREIGN KEY (object_type) REFERENCES object_type(id),
    FOREIGN KEY (object_subtype) REFERENCES object_subtype(id)
);

CREATE TABLE tech_econ_performance_factual (
     id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
     object_type int,
     object_subtype int,
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
     total_util_area real,
     PRIMARY KEY (id),
     FOREIGN KEY (object_type) REFERENCES object_type(id),
     FOREIGN KEY (object_subtype) REFERENCES object_subtype(id)
);

CREATE TABLE coefficient_normative (
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    project_id int NOT NULL,
    model_id varchar unique,
    tep_id bigint,
    flat_area_coeff real,
    comm_area_coeff real,
    ddu10_coeff real,
    residents_coeff real,
    child_coeff real,
    school_coeff real,
    ddu25_coeff real,
    playground_coeff real,
    sportground_coeff real,
    recreation_coeff real,
    util_coeff real,
    PRIMARY KEY (id),
    FOREIGN KEY(tep_id) references tech_econ_performance_normative(id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);

CREATE TABLE coefficient_factual (
     id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
     project_id int NOT NULL,
     model_id varchar unique,
     tep_id bigint,
     flat_area_coeff real,
     comm_area_coeff real,
     parking_flat_coeff real,
     parking_comm_coeff real,
     residents_coeff real,
     ddu10_coeff real,
     util_coeff real,
     PRIMARY KEY (id),
     FOREIGN KEY(tep_id) references tech_econ_performance_factual(id),
     FOREIGN KEY (project_id) REFERENCES projects(id)
);



CREATE OR REPLACE FUNCTION set_dt_creation()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.dt_creation IS NULL THEN
        NEW.dt_creation := CURRENT_TIMESTAMP;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER dt_creation
    before insert on projects
    FOR EACH ROW
EXECUTE FUNCTION set_dt_creation();


CREATE OR REPLACE FUNCTION set_dt_update()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.dt_update := CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_set_dt_update
    BEFORE UPDATE ON projects
    FOR EACH ROW
EXECUTE FUNCTION set_dt_update();


select version()
