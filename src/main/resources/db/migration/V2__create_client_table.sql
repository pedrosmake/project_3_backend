CREATE TABLE Client (
    id int PRIMARY KEY NOT NULL auto_increment,
    name varchar(50) NOT NULL,
    address varchar(70),
    phone_number varchar(20),
    create_date date NOT NULL,
    acquired_by int
);