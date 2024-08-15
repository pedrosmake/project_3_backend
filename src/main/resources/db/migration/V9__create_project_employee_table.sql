CREATE TABLE Project_Employee (
	id int PRIMARY KEY AUTO_INCREMENT,
    project_id int NOT NULL,
    employee_id int NOT NULL,
    start_date date NOT NULL,
    end_date date
);