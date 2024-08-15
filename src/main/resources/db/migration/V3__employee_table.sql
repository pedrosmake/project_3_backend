CREATE TABLE Employee (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    salary DECIMAL(6,2) NOT NULL,
    bank_account_number VARCHAR(28) NOT NULL,
    national_insurance_number VARCHAR(30) NOT NULL
);