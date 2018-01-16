--<ScriptOptions statementTerminator="!"/>

CREATE SCHEMA NAV226!

CREATE TABLE NAV226.dept (
		deptid NUMERIC NOT NULL,
		dname CHAR(15),
		location CHAR(15)
	)
	DATA CAPTURE NONE!

CREATE TABLE NAV226.student (
		snum NUMBER NOT NULL,
		sname CHAR(15),
		deptid NUMBER NOT NULL,
		slevel NUMBER,
		age NUMBER
	)
	DATA CAPTURE NONE!

CREATE TABLE NAV226.fact (
		fid NUMBER NOT NULL,
		fname CHAR(15),
		deptid NUMBER NOT NULL
	)
	DATA CAPTURE NONE!

CREATE TABLE NAV226.class (
		cname CHAR(15) NOT NULL,
		time TIME,
		room CHAR(15),
		fid NUMBER NOT NULL,
		deptid NUMBER NOT NULL
	)
	DATA CAPTURE NONE!

CREATE TABLE NAV226.enroll (
		snum NUMBER NOT NULL,
		cname CHAR(15) NOT NULL,
		grade CHAR(5),
		deptid NUMBER NOT NULL,
		fid NUMBER NOT NULL
	)
	DATA CAPTURE NONE!

ALTER TABLE NAV226.dept ADD CONSTRAINT DEPT_PK PRIMARY KEY
	(deptid)!

ALTER TABLE NAV226.student ADD CONSTRAINT student_PK PRIMARY KEY
	(deptid,
	 snum)!

ALTER TABLE NAV226.fact ADD CONSTRAINT fact_PK PRIMARY KEY
	(deptid,
	 fid)!

ALTER TABLE NAV226.class ADD CONSTRAINT class_PK PRIMARY KEY
	(deptid,
	 fid,
	 cname)!

ALTER TABLE NAV226.enroll ADD CONSTRAINT enroll_PK PRIMARY KEY
	(deptid,
	 snum,
	 fid,
	 cname)!

ALTER TABLE NAV226.student ADD CONSTRAINT student_DEPT_FK FOREIGN KEY
	(deptid)
	REFERENCES NAV226.dept
	(deptid)
	ON DELETE CASCADE!

ALTER TABLE NAV226.fact ADD CONSTRAINT fact_DEPT_FK FOREIGN KEY
	(deptid)
	REFERENCES NAV226.dept
	(deptid)
	ON DELETE CASCADE!

ALTER TABLE NAV226.class ADD CONSTRAINT class_fact_FK FOREIGN KEY
	(deptid,
	 fid)
	REFERENCES NAV226.fact
	(deptid,
	 fid)
	ON DELETE CASCADE!

ALTER TABLE NAV226.enroll ADD CONSTRAINT enroll_student_FK FOREIGN KEY
	(deptid,
	 snum)
	REFERENCES NAV226.student
	(deptid,
	 snum)
	ON DELETE CASCADE!

ALTER TABLE NAV226.enroll ADD CONSTRAINT enroll_class_FK FOREIGN KEY
	(deptid,
	 fid,
	 cname)
	REFERENCES NAV226.class
	(deptid,
	 fid,
	 cname)
	ON DELETE CASCADE!

COMMENT ON TABLE NAV226.dept IS
'
'!

