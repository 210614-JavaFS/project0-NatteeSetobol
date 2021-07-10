package com.revature.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.Database;
import com.revature.model.User;

public class UserController {
	private  Database database = null;
	
	UserController()
	{
		try {
			database = Database.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	User Register(User user)
	{
		try
		{
			String query = "insert into public.user VALUES( (select count(id)+1 from public.user), '" + user.getUsername() + "','" + user.getPassword() + "')";
			ResultSet resultSet = database.ExecuteStatement(query);
			
			query = "select count(id) as rowcount from public.user ";
			ResultSet maxSet = database.ExecuteStatement(query);
			maxSet.next();
			user.setID(maxSet.getInt("rowcount"));
			
			
		} catch(Exception e)
		{
			
		}
		return user;
	}
}
