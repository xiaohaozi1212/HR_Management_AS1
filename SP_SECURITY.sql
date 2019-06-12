create or replace
PACKAGE P_SECURITY AS
TYPE cur_EmpInfo IS REF CURSOR;

FUNCTION F_SECURITY(
P_SECID IN TBL_SECURITY.SEC_ID%TYPE,
P_SECPASSWORD IN TBL_SECURITY.SEC_PASSWORD%TYPE)
RETURN NUMBER;

PROCEDURE P_EMP_INFO (
P_EMPLOYEEID IN EMPLOYEES.EMPLOYEE_ID%TYPE, 
p_info OUT cur_EmpInfo);

END P_SECURITY;

create or replace PACKAGE BODY P_SECURITY AS

  FUNCTION F_SECURITY(
P_SECID IN TBL_SECURITY.SEC_ID%TYPE,
P_SECPASSWORD IN TBL_SECURITY.SEC_PASSWORD%TYPE)
RETURN NUMBER AS
v_validLogin Number(5);
  BEGIN
    v_validLogin := 0;

    SELECT count(*)  
      INTO v_validLogin  
      FROM TBL_SECURITY
     WHERE sec_id = P_SECID
       AND sec_password = P_SECPASSWORD
       AND sec_status = 'A';
    RETURN v_validLogin;
    
EXCEPTION 
    WHEN NO_DATA_FOUND THEN
        RETURN 0;
  END F_SECURITY;

  PROCEDURE P_EMP_INFO (
    P_EMPLOYEEID IN EMPLOYEES.EMPLOYEE_ID%TYPE, 
    p_info OUT cur_EmpInfo) AS
  BEGIN
    OPEN p_info FOR 
  SELECT 
	     EMPLOYEE_ID                                               
		,FIRST_NAME                                                
		,LAST_NAME                                                 
		,EMAIL                                                     
		,PHONE_NUMBER                                              
		,HIRE_DATE                                                 
		,JOB_ID                                                    
		,SALARY                                                    
		,COMMISSION_PCT                                            
		,MANAGER_ID                                                
		,DEPARTMENT_ID   
    FROM employees
   WHERE employee_id = P_EMPLOYEEID;

EXCEPTION WHEN NO_DATA_FOUND THEN
  p_info := NULL;
  END P_EMP_INFO;

END P_SECURITY;