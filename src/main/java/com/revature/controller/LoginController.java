package com.revature.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.Database;
import com.revature.model.User;
import com.revature.repos.UserDaoImp;

public class LoginController {

	private static Scanner scan = new Scanner(System.in);

	public User login(User user) 
	{
		UserDaoImp userDao = null;
		User loginUser = null;
		
		userDao = new UserDaoImp();
		
		loginUser = userDao.Login(user.getUsername(), user.getPassword());
		/*
		// TODO:()
		// 1. Get Username from database and password from database
		// 2. check if username and password matches.
		Database database  = null;
		try {
			database = Database.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		
		if (database != null)
		{
			ResultSet resultSet = database.ExecuteStatement("select id,username,password from public.user");
			try {
				while (resultSet.next())
				{
					String username = resultSet.getString("username");
					String password = resultSet.getString("password");
					int id = resultSet.getInt("id");
				
					if (username.equals(user.getUsername()))
					{
						if (password.equals(user.getPassword()))
						{
								user.setID(id);
								return user;
						}
					
					} else {
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		*/
		return loginUser;
	}

	public User inputUserPassword(User user) {
		String userPassword = null;
		
		userPassword = scan.nextLine();
		user.setPassword(userPassword);
		return user;
	}

	public User inputUserName(User user) 
	{
		String userName = null;
		if (user == null)
		{
			user = new User();
		}
		
		userName = scan.nextLine();
		user.setUsername(userName);
		
		return user;
	}

}
