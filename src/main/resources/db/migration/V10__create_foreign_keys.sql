ALTER TABLE Client
ADD CONSTRAINT fk_client_aquiredby
	FOREIGN KEY(acquired_by)
    REFERENCES Employee(id);

ALTER TABLE Commission_Rate
ADD CONSTRAINT fk_commission_rate_employeeid
	FOREIGN KEY(id)
    REFERENCES Employee(id);

ALTER TABLE Project
ADD CONSTRAINT fk_project_clientid
	FOREIGN KEY(client_id)
    REFERENCES Client(id);

ALTER TABLE Project
ADD CONSTRAINT fk_project_techlead
	FOREIGN KEY(techlead_id)
    REFERENCES Employee(id);

ALTER TABLE Project_Technology
ADD CONSTRAINT fk_project_technology_technologyid
	FOREIGN KEY(technology_id)
    REFERENCES Technology(id);

ALTER TABLE Project_Technology
ADD CONSTRAINT fk_project_technology_projectid
	FOREIGN KEY(project_id)
    REFERENCES Project(id);


ALTER TABLE Employee_Role
ADD CONSTRAINT fk_employee_role_roleid
	FOREIGN KEY(role_id)
    REFERENCES Role(id);

ALTER TABLE Employee_Role
ADD CONSTRAINT fk_employee_role_employeeid
	FOREIGN KEY(employee_id)
    REFERENCES Employee(id);


ALTER TABLE Project_Employee
ADD CONSTRAINT fk_project_employee_projectid
	FOREIGN KEY(project_id)
    REFERENCES Project(id);

ALTER TABLE Project_Employee
ADD CONSTRAINT fk_project_employee_employeeid
	FOREIGN KEY(employee_id)
    REFERENCES Employee(id);