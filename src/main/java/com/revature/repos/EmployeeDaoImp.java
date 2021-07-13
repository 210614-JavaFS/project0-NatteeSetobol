package com.revature.repos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.revature.Database;
import com.revature.model.AccountInformation;
import com.revature.model.Customer;
import com.revature.model.User;

public class EmployeeDaoImp implements EmployeeDao{

	Database database = null;
	public EmployeeDaoImp()
	{
		try {
			database = Database.getInstance();
		} catch (SQLException e) {
		}
	}
	@Override
	public boolean isEmployee(User user) {
		boolean result = false;
		
		ResultSet resultSet = database.ExecuteStatement("select uid from employees");
		
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
	@Override
	public ArrayList<HashMap<String, Object>> getAccountToApprove(User user) {
		ArrayList<HashMap<String, Object>> userList = new ArrayList<HashMap<String, Object>>();
		ResultSet resultSet = database.ExecuteStatement("select * from public.accounts where approved = false");
		
		try {
			while (resultSet.next())
			{
				HashMap<String, Object> hashMap = new HashMap<String,Object>();
				
				AccountInformation accountInformation = new AccountInformation();
				accountInformation.setId(resultSet.getInt("id"));
				accountInformation.setUid(resultSet.getInt("uid"));
				accountInformation.setBalance(resultSet.getInt("balance"));
				accountInformation.setAccounttype(resultSet.getString("type"));
				accountInformation.setNumber(resultSet.getInt("number"));
				hashMap.put("account", accountInformation);
				
				ResultSet userSet = database.ExecuteStatement("select * from public.users");
				while (userSet.next())
				{
					if (userSet.getInt("id") == accountInformation.getUid())
					{
						User accountUser = new User();
						accountUser.setID(userSet.getInt("id"));
						accountUser.setUsername(userSet.getString("username"));
						accountUser.setPassword(userSet.getString("password"));
						hashMap.put("user", accountUser);
					}
				}
				userList.add(hashMap);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userList;
	}

	@Override
	public void approveAccount(HashMap<String, Object> currentMap) 
	{
		AccountInformation accountInformtion = (AccountInformation) currentMap.get("account"); 
		ResultSet resultSet = database.ExecuteStatement("update accounts set approved = true where id=" + accountInformtion.getId());
	}

	@Override
	public void disapproveAccount(HashMap<String, Object> currentMap) 
	{
		AccountInformation accountInformtion = (AccountInformation) currentMap.get("account"); 
		ResultSet resultSet = database.ExecuteStatement("update accounts set approved = false where id=" + accountInformtion.getId());
	}
	
	@Override
	public Customer getUsernameInformation(User user, String username) {
		Customer customerResult = null;
		ResultSet resultSet = database.ExecuteStatement("select * from public.customers");
		
		try {
			while(resultSet.next())
			{
				int uId = resultSet.getInt("uid");
				
				if (uId == user.getID())
				{
					customerResult = new Customer();
					customerResult.setID(resultSet.getInt("id"));
					customerResult.setFirst(resultSet.getString("first"));
					customerResult.setLast(resultSet.getString("last"));
					customerResult.setAddress(resultSet.getString("address"));
					customerResult.setCity(resultSet.getString("city"));
					customerResult.setState(resultSet.getString("state"));
					customerResult.setPhoneNumber(resultSet.getString("phone_number"));
					customerResult.setEmail(resultSet.getString("email"));
					customerResult.setDateOfBirth(resultSet.getString("birthdate"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerResult;
	}
	
	@Override
	public User getUserByUsername(String choice) {
		boolean found = false;
		User resultUser = null;
		ResultSet resultSet = database.ExecuteStatement("select * from public.users");
		
		try {
			while(resultSet.next())
			{
				String username = resultSet.getString("username");
				
				if (username.equals(choice))
				{
					resultUser = new User();
					resultUser.setID(resultSet.getInt("id"));
					resultUser.setUsername(resultSet.getString("username"));
					found = true;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (found == false)
		{
			choice = null;
		}

		return resultUser;
	}
}
