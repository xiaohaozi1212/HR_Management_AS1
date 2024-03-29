DROP TABLE TBL_SECURITY CASCADE CONSTRAINTS PURGE;  
create table TBL_SECURITY(
    EMPLOYEE_ID NUMBER(6),
    SEC_ID VARCHAR2(20),
    SEC_PASSWORD VARCHAR2(10),
    SEC_STATUS CHAR(1)
)

ALTER TABLE TBL_SECURITY
ADD (CONSTRAINT sec_emp_id_fk
       		 FOREIGN KEY (EMPLOYEE_ID)
        	  REFERENCES EMPLOYEES (EMPLOYEE_ID)
     ) ;
     
INSERT INTO TBL_SECURITY VALUES(201, 'servlet', 'servlet', 'I');
INSERT INTO TBL_SECURITY VALUES(202, 'java', 'java', 'I');
INSERT INTO TBL_SECURITY VALUES(203, 'hr', 'hr', 'A');