package com.revature;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static Database instance = null;
	private static Connection connection;
	private static String sqlUrl = "jdbc:postgresql://sherlock.cf7ywxuuiask.us-east-2.rds.amazonaws.com:5432/bank";
	private static String sqlUser = "postgres";
	private static String sqlPassword = "password";
	
	public Database() throws SQLException
	{

	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public ResultSet ExecuteStatement(String queryString)
	{
		 ResultSet result = null;
		 Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		 try {
			result = statement.executeQuery(queryString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}	
		
		 return result;
	}
	
	public static Database getInstance() throws SQLException
	{
		if (instance == null)
		{
			instance = new Database();
			try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(sqlUrl,sqlUser,sqlPassword);
			} catch (Exception ex)
			{
				System.err.println(ex.getMessage());
			}
		}
		
		return instance;
	}
}
