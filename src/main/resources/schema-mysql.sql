DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  empId VARCHAR(10) NOT NULL,
  empName VARCHAR(100) NOT NULL
);

INSERT INTO employee(empId,empName)values("111","aaa");
INSERT INTO employee(empId,empName)values("222","bbb");
INSERT INTO employee(empId,empName)values("333","ccc");