package com.romel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DbUtilFunctionalTest {
	
	private DbUtil dbUtil = null;
	private Properties properties = null;
	
	@BeforeEach
	public void setUp() throws SQLException, FileNotFoundException, IOException {
		if(dbUtil == null) {
			properties = new Properties();
			InputStream inputStream = new FileInputStream(new File("./config/dbconfig.properties"));
			properties.load(inputStream);
			dbUtil = new DbUtil(properties.getProperty("mysqllocal.url"), properties.getProperty("mysqllocal.schema"),
					properties.getProperty("mysqllocal.username"), properties.getProperty("mysqllocal.password"));
		}
	}
	
	@Test
	public void testStart() {
		String sql;
		String[] columnType;
		String[] columnValue;
		try {
			System.out.println("Functional test started for -> " + dbUtil.getClass().getName());
			System.out.println("Data Source -> " + properties.getProperty("mysqllocal.url") + "/" + properties.getProperty("mysqllocal.schema"));
			System.out.println("Run Date and Time -> " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + 
					" " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
			
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("Method under test -> public void connect() {}");
			System.out.println("Query String: None");
			System.out.println("Commments: Connects to the MySQL local database.");
			System.out.println("Result:\n");
			dbUtil.connect();
			System.out.println("Successfull.");
			
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("Method under test -> public ResultSet getData(String sql) {}");
			System.out.println("Query String: Select * From employees Limit 5");
			System.out.println("Comments: Retrieves the first five employee records.");
			System.out.println("Result:\n");
			getResultSet("Select * From employees Limit 5");
			System.out.println("Successfull.");
			
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("Method under test -> public ResultSet getData(String sql, String dataType, String argument) {}");
			System.out.println("Query String: select employeeNumber, concat(firstName, \" \", lastName) as \"Name\", city from employees " + "\n" +
					"left join offices on employees.officeCode = offices.officeCode where employeeNumber > 1500");
			System.out.println("Comments: Retrieves the name and city of employees where the employee number is greater than 1500.");
			System.out.println("Result:\n");
			displayResultSet(getResultSet("select employeeNumber, concat(firstName, \" \", lastName) as \"Name\", city from employees left join offices on employees.officeCode = offices.officeCode " + 
			"where employeeNumber > ?", "String", "1500"));
			System.out.println("Test Successfull.");
			
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("Method under test -> public ResultSet getData(String sql) {}");
			System.out.println("Query String: Select Count(employeeNumber) From employees");
			System.out.println("Comments: Retrieves the total number of records in the employees table.");
			System.out.println("Result:\n");
			displayResultSet(getResultSet("Select Count(employeeNumber) From employees"));
			System.out.println("Test Successfull.");
			
			System.out.println("--------------------------------------------------------------------------------------------");
			sql = "Insert Into employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) " +
					"values (?, ?, ?, ?, ?, ?, ?, ?)";
			columnType = new String[]{"int", "String", "String", "String", "String", "int", "int", "String"};
			columnValue = new String[]{"1803", "Boi", "Mushroom", "x1234", "mboi@classicmodelcars.com", "1", "1002", "Security Officer"};
			System.out.println("Method under test -> public int updateData(String sql, String[] dataType, String[] argument) {}");
			System.out.println("Query String: " + sql);
			System.out.println("Comments: Inserts a record in the employees table for employee number 1803.");
			System.out.println("Result:\n");
			//updateData(sql, columnType, columnValue);
			System.out.println("Inserted -> " + updateData(sql, columnType, columnValue) + " record/s.");
			System.out.println("Test Successfull.");
			
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("Method under test -> public ResultSet getData(String sql) {}");
			System.out.println("Query String: Select Count(employeeNumber) From employees");
			System.out.println("Comments: Retrieves the total number of records in the employees table.");
			System.out.println("Result:\n");
			displayResultSet(getResultSet("Select Count(employeeNumber) From employees"));
			System.out.println("Test Successfull.");
			
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("Method under test -> public ResultSet getData(String sql) {}");
			System.out.println("Query String: Select * From employees Where employeeNumber = 1803");
			System.out.println("Comments: Retrieves the newly inserted record for employee number 1803.");
			System.out.println("Result:\n");
			displayResultSet(getResultSet("Select * From employees Where employeeNumber = ?", "int", "1803"));
			System.out.println("Successfull.");
			
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("Method under test -> public void close() {}");
			System.out.println("Query String: None");
			System.out.println("Comments: Closes the connection to the MySQL local database.");
			System.out.println("Result:\n");
			dbUtil.close();
			System.out.println("Test Successfull.");
			
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("End of test.");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			fail(ex.toString());
		}
		catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.toString());
		}
	}
	
	public ResultSet getResultSet(String sql) throws SQLException, Exception {
		return dbUtil.getData(sql);
	}
	
	public ResultSet getResultSet(String sql, String dataType, String argument) throws SQLException, Exception {
		return dbUtil.getData(sql, dataType, argument);
	}
	
	public void displayResultSet(ResultSet resultSet) throws SQLException, Exception {
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		
		if(resultSet != null & resultSet.isBeforeFirst()) {
			for(int i = 1; i <= resultSetMetaData.getColumnCount(); i ++) {
				System.out.printf("%-35s", resultSetMetaData.getColumnLabel(i));
			}
			
			System.out.print("\n");
			
			while(resultSet.next()) {
				for(int i = 1; i <= resultSetMetaData.getColumnCount(); i ++) {
					System.out.printf("%-35s", resultSet.getString(i));
				}
				System.out.print("\n");
			}
		}
		else {
			System.out.println("0 number of records retrieved.");
		}
	}
	
	public int updateData(String sql, String[] dataType, String[] argument) throws SQLException, Exception {
		return dbUtil.updateData(sql, dataType, argument);
	}

}





























































































































































































































































































