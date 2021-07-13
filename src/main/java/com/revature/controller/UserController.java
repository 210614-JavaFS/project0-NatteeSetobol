package com.revature.controller;

//import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.Database;
import com.revature.model.User;
import com.revature.repos.UserDaoImp;

public class UserController {
	private  Database database = null;
	private UserDaoImp userDao = null;
	
	UserController()
	{
		userDao = new UserDaoImp();
		try {
			database = Database.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	User Register(User user)
	{
		User result = null;
		
		result = userDao.AddNewUser(user.getUsername(), user.getPassword());

		return result;
	}
}
