package ca.myseneca.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ca.myseneca.model.DBUtil;
import ca.myseneca.model.Employee;
import oracle.jdbc.internal.OracleTypes;

public class batchUpdateTest {

	static final String USERID = "cjv805_191a24";
	static final String PASSWORD = "10671091";
	static final String DRIVER_NAME = "oracle.jdbc.OracleDriver"; // JDBC driver

	public static void main(String[] args) throws Exception {
		Employee emp = new Employee();
		emp.setEmployee_id(411);
		emp.setLast_name("chen2");
		emp.setEmail("yong");
		emp.setJob_id("AC_ACCOUNT");
		emp.setHire_date(java.sql.Date.valueOf("2013-09-04"));
		
		
		Employee emp2 = new Employee();
		emp2.setEmployee_id(412);
		emp2.setLast_name("chen2");
		emp2.setEmail("yo");
		emp2.setJob_id("AC_ACCOUNT");
		emp2.setHire_date(java.sql.Date.valueOf("2013-09-04"));
		
		Employee emp3 = new Employee();
		emp3.setEmployee_id(412);
		emp3.setLast_name("chen2");
		emp3.setEmail("yongh");
		emp3.setJob_id("AC_ACCOUNT");
		emp3.setHire_date(java.sql.Date.valueOf("2013-09-04"));

		System.out.println(emp);
		System.out.println(emp2);
		System.out.println(emp3);
		
		ArrayList<Employee> EmployeeList = new ArrayList<Employee>();
		EmployeeList.add(emp);
		EmployeeList.add(emp2);
		EmployeeList.add(emp3);
		
		//addEmployeeList(EmployeeList);
		// System.out.println(i);
		deleteEmployee();

	}

	public static void  batchUpdate(String[] SQLs) {
			
			Connection con = DBUtil.getConnection();
			PreparedStatement pst = null;
				
			try {
				con.setAutoCommit(false);
				String sql = "INSERT INTO employees (employee_id,last_name,email,hire_date,job_id) VALUES (?,?,?,?,?)";
				
				pst = con.prepareStatement(sql);
				
				for(int i = 0 ; i < SQLs.length ; i ++ ) {
					
					
				}
				
				
				System.out.println(" rows inserted "+ pst.executeUpdate());
				// pst2.executeUpdate();
				 con.commit();
				// Execute the update statement
				 System.out.println(" rows inserted ");

				
			} catch (SQLException se) {
				System.out.println("Exception :" + se.getMessage());
				se.printStackTrace();

			} catch (Exception e) {
				System.out.println("Exception :" + e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (pst != null)
						pst.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
				}

			}
			
		}

	public static void deleteEmployee() {

		PreparedStatement pst = null;
		Connection con = null;
		try {
			Class.forName(DRIVER_NAME);
			String url = "jdbc:oracle:thin:@myoracle12c.senecacollege.ca:1521:oracle12c";
			con = DriverManager.getConnection(url, USERID, PASSWORD);

			System.out.println("start");
			con.setAutoCommit(false);

			String sql = "delete from employees where employee_id = ?";

			// PreparedStatement to set parameter
			pst = con.prepareStatement(sql);
			pst.setObject(1, 324);
			// Execute the update statement
			pst.executeUpdate();
			// pst.executeUpdate(sql);

			con.commit();

			// Statement stmt = con.createStatement ();
			// boolean i = stmt.execute ("delete from cyh where cyh_id = 100");

			System.out.println("end ");
			// System.out.println( i);
		} catch (SQLException se) {
			// Handle errors for JDBC
			System.out.println("Exception :" + se.getMessage());
			se.printStackTrace();

		} catch (Exception e) {
			// Handle errors for Class.forName
			System.out.println("Exception :" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		}

	}

}
