package com.revature;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static Database instance = null;
	private Connection connection;
	private String sqlUrl = "jdbc:postgresql://localhost:5432/Bank";
	private String sqlUser = "postgres";
	private String sqlPassword = "12345";
	
	public Database() throws SQLException
	{
		try {
			connection = DriverManager.getConnection(sqlUrl,sqlUser,sqlPassword);
		} catch (SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	public ResultSet ExecuteStatement(String queryString)
	{
		 ResultSet result = null;
		 Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			result = statement.executeQuery(queryString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		 return result;
	}
	
	public static Database getInstance() throws SQLException
	{
		if (instance == null)
		{
			instance = new Database();
		}
		
		return instance;
	}
}
