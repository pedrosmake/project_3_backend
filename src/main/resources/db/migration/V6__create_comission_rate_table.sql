CREATE TABLE Commission_Rate (
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    value decimal(5,2),
    CHECK (value >= 0),
    CHECK (value <= 100)
);