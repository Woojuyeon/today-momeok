DROP TABLE UserInfo CASCADE CONSTRAINTS;
DROP TABLE Community CASCADE CONSTRAINTS;
DROP SEQUENCE commId_seq;

CREATE TABLE UserInfo ( 
	userId      VARCHAR2(12)	PRIMARY KEY, 
	password	VARCHAR2(12)	NOT NULL,
	name		VARCHAR2(20)	NOT NULL,
	email		VARCHAR2(50),	
 	phone		VARCHAR2(20),
 	commId		NUMBER(4)
);

CREATE TABLE Community ( 
	cId     	NUMBER(4)		PRIMARY KEY, 
	cName		VARCHAR2(20)	NOT NULL,
	descr		VARCHAR2(50),
	startDate	Date,
	chairId		VARCHAR2(12)	
);

ALTER TABLE UserInfo ADD FOREIGN KEY (commId) REFERENCES Community (cId);
ALTER TABLE Community ADD FOREIGN KEY (chairId) REFERENCES UserInfo (userId);

CREATE SEQUENCE commId_seq
	START WITH 10
	INCREMENT BY 10; 
	
INSERT INTO Community VALUES (commId_seq.NEXTVAL, 'Cinema Paradiso', '영화를 사랑하는 사람들의 모임', SYSDATE, null);
INSERT INTO Community VALUES (commId_seq.NEXTVAL, 'ARMY', 'BTS 팬클럽', SYSDATE, null);
INSERT INTO Community VALUES (commId_seq.NEXTVAL, 'PageTurner', '북클럽', SYSDATE, null);
INSERT INTO Community VALUES (commId_seq.NEXTVAL, 'ILoveDBP', 'Database Programming Study Group', SYSDATE, null);

INSERT INTO UserInfo VALUES ('admin', 'admin', '시스템 관리자', 'admin@dongduk.ac.kr', '02-940-9999', null);
INSERT INTO UserInfo VALUES ('movieMania', 'movie123', '이화영', 'young01@naver.com', '010-1234-5678', 10);
INSERT INTO UserInfo VALUES ('emma', 'emma123', 'Emma Stone', 'emma88@gmail.com', '787-110-6200', 20);
INSERT INTO UserInfo VALUES ('julian', 'julian123', 'Julian Barnes', 'barnes@hotmail.com', '778-443-1532', 30);
INSERT INTO UserInfo VALUES ('paul', 'paul123', 'Paul Auster', 'paul@gmail.com', '520-342-5566', 30);
INSERT INTO UserInfo VALUES ('mina', 'mina123', '김미나', 'mnkim@dongduk.ac.kr', '010-6677-2233', 40);

UPDATE Community SET chairId = 'movieMania' WHERE cid = 10;
UPDATE Community SET chairId = 'julian' WHERE cid = 30;

COMMIT;
