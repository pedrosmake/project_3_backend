CREATE TABLE `User` (
	Username VARCHAR(64) NOT NULL,
    Password VARCHAR(64) NOT NULL,
    RoleId TINYINT NULL NULL,
    PRIMARY KEY (Username),
    FOREIGN KEY ( RoleID) REFERENCES AuthRole(RoleID)
);
