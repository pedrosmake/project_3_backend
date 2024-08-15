CREATE TABLE Project_Technology (
    technology_id INT NOT NULL,
    project_id INT NOT NULL,
    PRIMARY KEY (project_id, technology_id)
);