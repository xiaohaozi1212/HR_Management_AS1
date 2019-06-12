
package ca.myseneca.rmi.client;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

import ca.myseneca.model.DBAccessHelper;
import ca.myseneca.model.Employee;

/**
 * Client This class is the client side code which is identical to the
 * HRManagment class.
 * 
 * @author Shweta Shrestha, Yonghau Chen
 *
 */
public class Client_s {

	/**
	 * This is the main method that calls all the functions and interacts with the
	 * Server.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		DBAccessHelper obj = (DBAccessHelper) Naming.lookup("rmi://localhost:5008/HRManagementService");

		Scanner reader = new Scanner(System.in);
		String exit = "no";

		while (exit.equalsIgnoreCase("no")) {
			System.out.println("Please Enter the Username:");
			String username = reader.nextLine();
			System.out.println("Please Enter the Password:");
			String password = reader.nextLine();
			System.out.println("Connecting to the Database...");
			int emp_id = obj.getEmployeeID(username, password);
			if (emp_id != 0) {

				System.out.println("Connected to the database!");

				System.out.println("*********************");
				System.out.println("Employee Information: ");
				getEmployeeByID(obj, 304);
				System.out.println("*********************");
				System.out.println("Adding a New Employee: ");
				addEmployees(obj);
				System.out.println("*********************");
				System.out.println("Deleting the Employee: ");
				deleteEmployees(obj);
				System.out.println("*********************");
				System.out.println("Updating Employee row: ");
				updateEmployees(obj);

				System.out.println("*********************");
				System.out.println("Batch Update: ");
				batchUpdate(obj);

				System.out.println("*********************");
				System.out.println("Retrieve All Employees: ");
				getAllEmployees(obj);
				System.out.println("*********************");
				System.out.println("Retrieve Total count of employees for department: ");
				getEmployeesByDepartmentID(obj);
				System.out.println("*********************");
			} else {
				System.out.println("*********************");
				System.out.println("Invalid Username and Password.");
				System.out.println("*********************");
			}
			System.out.println("Do you want to exit? yes/no");
			exit = reader.nextLine();
		}
		

	}

	/**
	 * This method updates an employee row.
	 * 
	 * @param DBAccessHelper dbaccess
	 * @throws Exception
	 */
	public static void updateEmployees(DBAccessHelper dbaccess) throws Exception {
		Employee emp = new Employee();
		emp.setEmail("5test@email.com");
		emp.setLast_name("Liu");
		emp.setJob_id("AC_ACCOUNT");
		emp.setHire_date(java.sql.Date.valueOf("2013-09-04"));
		emp.setEmployee_id(403);
		System.out.println("Updating Employee: " + emp.toString());

		System.out.println("Rows Updated:"+dbaccess.updateEmployee(emp));
	}

	/**
	 * This method calls the batch update function.
	 * 
	 * @param DBAccessHelper dbaccess
	 * @throws Exception
	 */
	public static void batchUpdate(DBAccessHelper dbaccess) throws Exception {
		String[] sqls = new String[6];
		sqls[0] = "delete from employees where EMPLOYEE_ID = 901";
		sqls[1] = "delete from employees where EMPLOYEE_ID = 911";
		sqls[2] = "delete from employees where EMPLOYEE_ID = 981";
		sqls[3] = "INSERT INTO employees(employee_id,last_name,email,hire_date,job_id) VALUES (500,'Smith','5smith@email.com',TO_DATE('07-JUN-1994', 'dd-MON-yyyy'),'AC_ACCOUNT')";
		sqls[4] = "INSERT INTO employees(employee_id,last_name,email,hire_date,job_id) VALUES (501,'Sal','5sal@email.com',TO_DATE('07-JUN-1994', 'dd-MON-yyyy'),'AC_ACCOUNT')";
		sqls[5] = "update employees  set last_name = 'c00',email = 'dc00d',hire_date = TO_DATE('07-JUN-1994', 'dd-MON-yyyy'),job_id ='AC_ACCOUNT'  where employee_id = 554";
		System.out.println("SQL Scripts:");
		for(String sql: sqls)
			System.out.println(sql);
		
		if(dbaccess.batchUpdate(sqls))
			System.out.println("Successfully executed the batch update.");
		else
			System.out.println("Failure to execute the batch update.");

	}

	/**
	 * This method inserts a new employee row.
	 * 
	 * @param DBAccessHelper dbaccess
	 * @throws Exception
	 */
	public static void addEmployees(DBAccessHelper dbaccess) throws Exception {
		Employee emp = new Employee();
		emp.setEmail("499che2112@email.com");
		emp.setLast_name("Chen");
		emp.setJob_id("AC_ACCOUNT");
		emp.setHire_date(java.sql.Date.valueOf("2013-09-04"));
		emp.setEmployee_id(999);
		System.out.println(emp);

		dbaccess.addEmployee(emp);
		System.out.println("Successfully Inserted.");
	}

	/**
	 * Deleting the employee added through the addEmployee functon.
	 * 
	 * @param DBAccessHelper dbaccess
	 * @throws Exception
	 */
	public static void deleteEmployees(DBAccessHelper dbaccess) throws Exception {

		System.out.println("Rows Deleted: "+dbaccess.deleteEmployeeByID(800));

	}

	/**
	 * This method retrieves all the employees from the database.
	 * 
	 * @param DBAccessHelper dbaccess
	 * @throws Exception
	 */
	public static void getAllEmployees(DBAccessHelper dbaccess) throws Exception {

		ArrayList<Employee> list = dbaccess.getAllEmployees();
		System.out.println("Total employee count: " + list.size());
	}

	/**
	 * This method retrieves total number of the employees from the department 80.
	 * 
	 * @param DBAccessHelper dbaccess
	 * @throws Exception
	 */
	public static void getEmployeesByDepartmentID(DBAccessHelper dbaccess) throws Exception {

		ArrayList<Employee> list = new ArrayList<Employee>();
		list = dbaccess.getEmployeesByDepartmentID(80);
		System.out.println("Total employee count for department 80: " + list.size());

	}

	/**
	 * This method gets the information of the employee.
	 * 
	 * @param DBAccessHelper dbaccess
	 * @param int            employee_id
	 * @throws Exception
	 */
	public static void getEmployeeByID(DBAccessHelper dbaccess, int emp_id) throws Exception {
		Employee emp = dbaccess.getEmployeeByID(emp_id);
		System.out.println(emp.toString());
	}

}