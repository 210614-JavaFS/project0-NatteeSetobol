package com.revature.repos;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.Database;
import com.revature.model.User;

public class AdminDaoImp extends EmployeeDaoImp implements AdminDao{

	private Database database  = null;
	public AdminDaoImp()
	{
		try {
			database = Database.getInstance();
		} catch (SQLException e) {
		}
	}
	
	@Override
	public boolean isAdmin(User user) {
		
		boolean result = false;
		ResultSet resultSet = database.ExecuteStatement("select uid from admins");
		
		try {
			while (resultSet.next())
			{
				int uid = resultSet.getInt("uid");
			
				if (uid == user.getID())
				{
					result = true;
					break;
				}
			}
			
		} catch (SQLException e) {
			result = false;
		}
		return result;
	}

}
