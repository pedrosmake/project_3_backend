CREATE TABLE Project (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    `value` DECIMAL(10,2),
    status ENUM('TODO', 'INPROGRESS', 'COMPLETED'),
    client_id INT,
    techlead_id INT
);