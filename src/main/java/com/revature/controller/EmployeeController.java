package com.revature.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.revature.Database;
import com.revature.model.AccountInformation;
import com.revature.model.Customer;
import com.revature.model.User;

public class EmployeeController {
	private static Scanner scan = new Scanner(System.in);
	private Database database;

	
	public EmployeeController() {
		super();
		try {
			database = Database.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Integer getEmployeeInput(User user, Integer min, Integer max) 
	{
		System.out.print("> ");
		Integer userInput = scan.nextInt();
		
		if (userInput >= min && userInput <= max)
		{
			
		} else {
			userInput = 0;
		}
		
		return userInput;
	}

	public ArrayList<HashMap<String, Object>> getAccountToApprove(User user) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> userList = new ArrayList<HashMap<String, Object>>();
		ResultSet resultSet = database.ExecuteStatement("select * from public.account where approved = false");
		
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
				
				ResultSet userSet = database.ExecuteStatement("select * from public.user");
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

	public boolean isEmployee(User user) {
		boolean result = false;
		ResultSet resultSet = database.ExecuteStatement("select * from public.employee");
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int Validate(User user) {
		System.out.print("> ");
		int choice;
		choice = scan.nextInt();
		return choice;
	}

	public User ValidateUser(User user) {
		boolean found = false;
		System.out.print("> ");
		String choice = null;
		User resultUser = null;

		choice = scan.nextLine();
		choice = scan.nextLine();
		ResultSet resultSet = database.ExecuteStatement("select * from public.user");
		
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

	public void approveAccount(HashMap<String, Object> currentMap) {
		AccountInformation accountInformtion = (AccountInformation) currentMap.get("account"); 
		ResultSet resultSet = database.ExecuteStatement("update account set approved = true where id=" + accountInformtion.getId());
	}

	public void disapproveAccount(HashMap<String, Object> currentMap) 
	{
		AccountInformation accountInformtion = (AccountInformation) currentMap.get("account"); 
		ResultSet resultSet = database.ExecuteStatement("update account set approved = false where id=" + accountInformtion.getId());
	}

	public Customer getUsernameInformation(User user, String username) {
		Customer customerResult = null;
		ResultSet resultSet = database.ExecuteStatement("select * from public.customer");
		
		try {
			while(resultSet.next())
			{
				int uId = resultSet.getInt("userid");
				
				if (uId == user.getID())
				{
					customerResult = new Customer();
					customerResult.setID(resultSet.getInt("id"));
					customerResult.setFirst(resultSet.getString("first"));
					customerResult.setLast(resultSet.getString("last"));
					customerResult.setAddress(resultSet.getString("address"));
					customerResult.setCity(resultSet.getString("city"));
					customerResult.setState(resultSet.getString("state"));
					customerResult.setPhoneNumber(resultSet.getString("phonenumber"));
					customerResult.setEmail(resultSet.getString("email"));
					customerResult.setDateOfBirth(resultSet.getString("dateofbirth"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerResult;
	}
}
