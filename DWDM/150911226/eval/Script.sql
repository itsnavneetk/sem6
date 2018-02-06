--<ScriptOptions statementTerminator="!"/>

CREATE SCHEMA eval226!

CREATE TABLE eval226.customer (
		custid INTEGER NOT NULL,
		name CHAR(15),
		country CHAR(15),
		age INTEGER
	)
	DATA CAPTURE NONE!

CREATE TABLE eval226.orders (
		orderid INTEGER NOT NULL,
		custid INTEGER,
		orderdate DATE,
		shipdate DATE,
		shipid INTEGER NOT NULL
	)
	DATA CAPTURE NONE!

CREATE TABLE eval226.shipment (
		shipid INTEGER NOT NULL,
		shipdate DATE
	)
	DATA CAPTURE NONE!

ALTER TABLE eval226.customer ADD CONSTRAINT customer_PK PRIMARY KEY
	(custid)!

ALTER TABLE eval226.orders ADD CONSTRAINT orders_PK PRIMARY KEY
	(shipid,
	 orderid)!

ALTER TABLE eval226.shipment ADD CONSTRAINT shipment_PK PRIMARY KEY
	(shipid)!

ALTER TABLE eval226.orders ADD CONSTRAINT orders_shipment_FK FOREIGN KEY
	(shipid)
	REFERENCES eval226.shipment
	(shipid)
	ON DELETE CASCADE!

ALTER TABLE eval226.orders ADD CONSTRAINT orders_customer_FK FOREIGN KEY
	(custid)
	REFERENCES eval226.customer
	(custid)
	ON DELETE CASCADE!

