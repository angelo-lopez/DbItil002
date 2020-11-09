package com.romel;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DbUtil {

	private Connection connection = null;
	private String url = null;
	private String schema = null;
	private String userName = null;
	private String passWord = null;
	
	public DbUtil() {
		url = "";
		schema = "";
		userName = "";
		passWord = "";
	}
	
	public DbUtil(String url, String schema, String userName, String passWord) {
		this.url = url;
		this.schema = schema;
		this.userName = userName;
		this.passWord = passWord;
	}

	public Connection getConnection() {
		return connection;
	}

	public String getUrl() {
		return url;
	}

	public String getSchema() {
		return schema;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public void setConnection(String url, String schema, String userName, String passWord) {
		this.url = url;
		this.schema = schema;
		this. userName = userName;
		this.passWord = passWord;
	}
	
	public void connect() throws SQLException, Exception {
		connection = DriverManager.getConnection(url + "/" + schema, userName, passWord);
	}
	
	public void close() throws SQLException, Exception {
		connection.close();
	}
	
	public ResultSet getData(String sql) throws SQLException, Exception {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}
	
	public ResultSet getData(String sql, String dataType, String argument) throws SQLException, Exception{
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		switch(dataType) {
		case "String":
			preparedStatement.setString(1, argument);
			break;
		case "int":
			preparedStatement.setInt(1, Integer.parseInt(argument));
			break;
		}
		
		return preparedStatement.executeQuery();
	}
	
	public ResultSet getData(String sql, String[] dataType, String[] argument) throws SQLException, Exception {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		for(int i = 0; i < argument.length; i ++) {
			switch(dataType[i]) {
			case "String":
				preparedStatement.setString((i + 1), argument[i]);
				break;
			case "int":
				preparedStatement.setInt((i + 1), Integer.parseInt(argument[i]));
				break;
			}
		}
		
		return preparedStatement.executeQuery();
	}
	
	public int updateData(String sql) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement.executeUpdate();
	}
	
	public int updateData(String sql, String[] dataType, String[] argument) throws SQLException, Exception {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		for(int i = 0; i < argument.length; i ++) {
			switch(dataType [i]) {
				case "String":
					preparedStatement.setString((i + 1), argument[i]);
					break;
				case "int":
					preparedStatement.setInt((i + 1), Integer.parseInt(argument[i]));
					break;
			}
		}
		
		return preparedStatement.executeUpdate();
	}
	
	public String[] getColumnNames(ResultSet resultSet) throws SQLException {
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		String[] columnNames = new String[resultSetMetaData.getColumnCount()];
		
		for(int i = 1; i <= resultSetMetaData.getColumnCount(); i ++) {
			columnNames[i - 1] = resultSetMetaData.getColumnName(i);
		}
		
		return columnNames;
	}
	
}














































































































































































































































