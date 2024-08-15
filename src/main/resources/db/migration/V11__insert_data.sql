INSERT INTO Employee (name, salary, bank_account_number, national_insurance_number)
    VALUES ('Bob Fast',9000, 'XX45576820393856709845982345', '1111111111111111111'),
        ('Ann Quick',8600, 'XX45534820393856709845982345', '1111111445111111111'),
        ('Rob Track',2000, 'XX45976820393856709845982345', '1111234111111111111'),
        ('Liz Dizzy',7000, 'XX45576820393856709811112345', '1111111111116781111'),
        ('Daisy Unter',5300.50, 'XX4557682039385670982222', '1111112311111111111');

INSERT INTO Technology (name)
    VALUES ('Java'),
        ('SQL'),
        ('React'),
        ('Docker'),
        ('HTML');

INSERT INTO Role(role)
    VALUES('DELIVERY'),
        ('TEAMLEAD'),
        ('HR'),
        ('SALES'),
        ('MANAGEMENT');

INSERT INTO Client (name, address, phone_number, create_date, acquired_by)
    VALUES ('Patric Cooper', 'Main St. 4 NY', '123-456-789', '2019-01-13', 1),
        ('Andrew Torry', 'Narrow St. 12 DC', '133-456-789', '2019-02-23', 2),
        ('Piotr Stol', 'Polna 2 Warszawa', '233-456-789', '2022-02-23', 3);

INSERT INTO Commission_Rate (id, value)
    VALUES (1,5.00),
        (2,10.00),
        (3,2.00),
        (4,5.50),
        (5,5.60);

INSERT INTO Project (name, value, status, client_id, techlead_id)
    VALUES ('Proj1', 300000, 'INPROGRESS', 1, 1),
            ('Proj2', 400000, 'TODO', 2, 2),
            ('Proj3', 200000, 'COMPLETED', 2, 1);

INSERT INTO Employee_Role (role_id, employee_id)
    VALUES (1,2),
        (2,2),
        (3,1),
        (4,3),
        (5,4);

INSERT INTO Project_Technology (technology_id, project_id)
    VALUES(1,1),
        (1,2),
        (1,3),
        (2,1),
        (3,2),
        (3,3),
        (4,1),
        (4,3),
        (5,1);

INSERT INTO Project_Employee (project_id, employee_id, start_date, end_date)
    VALUES(1,1, '2019-01-13', NULL),
    (1,2, '2019-01-17', '2020-04-14'),
    (2,1, '2023-01-13', NULL),
    (3,1, '2023-01-13', NULL);