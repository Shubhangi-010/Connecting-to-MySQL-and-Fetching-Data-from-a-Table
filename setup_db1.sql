
CREATE DATABASE jdbc_demo;

USE jdbc_demo;
CREATE TABLE Employee (
    EmpID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Salary DECIMAL(10, 2) NOT NULL
);
INSERT INTO Employee (Name, Salary) VALUES
('Jane Doe', 60000.00),
('John Smith', 75500.50),
('Alice Johnson', 82000.00),
('Bob Williams', 55000.00);
SELECT * FROM Employee;