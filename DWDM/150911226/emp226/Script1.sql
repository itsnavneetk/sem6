--<ScriptOptions statementTerminator="!"/>

CREATE SCHEMA emp226!

CREATE TABLE emp226.employee (
		empID INTEGER NOT NULL,
		name CHAR(15),
		street CHAR(15)
	)
	DATA CAPTURE NONE!

CREATE TABLE emp226.works (
		empID INTEGER NOT NULL,
		compID INTEGER NOT NULL,
		companyname CHAR(15),
		salary INTEGER
	)
	DATA CAPTURE NONE!

CREATE TABLE emp226.company (
		compID INTEGER NOT NULL,
		city CHAR(15)
	)
	DATA CAPTURE NONE!

CREATE TABLE emp226.manages (
		empID INTEGER,
		managerID INTEGER NOT NULL
	)
	DATA CAPTURE NONE!

ALTER TABLE emp226.employee ADD CONSTRAINT employee_PK PRIMARY KEY
	(empID)!

ALTER TABLE emp226.works ADD CONSTRAINT works_PK PRIMARY KEY
	(empID,
	 compID)!

ALTER TABLE emp226.company ADD CONSTRAINT company_PK PRIMARY KEY
	(compID)!

ALTER TABLE emp226.manages ADD CONSTRAINT manages_PK PRIMARY KEY
	(managerID)!

ALTER TABLE emp226.employee ADD CONSTRAINT employee_employee_FK FOREIGN KEY
	(empID)
	REFERENCES emp226.employee
	(empID)
	ON DELETE CASCADE!

ALTER TABLE emp226.works ADD CONSTRAINT works_employee_FK FOREIGN KEY
	(empID)
	REFERENCES emp226.employee
	(empID)
	ON DELETE CASCADE!

ALTER TABLE emp226.works ADD CONSTRAINT works_company_FK FOREIGN KEY
	(compID)
	REFERENCES emp226.company
	(compID)
	ON DELETE CASCADE!

