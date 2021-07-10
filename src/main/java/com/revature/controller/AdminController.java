package com.revature.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.revature.Database;
import com.revature.model.AccountInformation;
import com.revature.model.User;

public class AdminController {
	private Database database;
	private static Scanner scan = new Scanner(System.in);

	public AdminController()
	{
		try {
			database = Database.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public boolean isAdmin(User user)
	{
		boolean result = false;
		
		
		ResultSet resultSet = database.ExecuteStatement("select uid from admin");
		
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
	
	public boolean isEmployee(User user)
	{
		boolean result = false;
		
		
		ResultSet resultSet = database.ExecuteStatement("select uid from employee");
		
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

	
	
	public int Validate(User user)
	{
		System.out.print("> ");
		int choice;
		choice = scan.nextInt();
		switch(choice)
		{
			case 1:
			{
				break;
			}
		}
		
		return choice;
	}

	public ArrayList<HashMap<String, Object>> getAccountToApprove(User user) {
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

	public boolean approveUser(User validUser, int accountType) {
		boolean result = false;
		//if (isAdmin(validUser))
		{
			String typeInString = null;
			
			if (accountType == 0)
			{
				typeInString = "Customer";
			} else {
				typeInString = "Employee";
			}
			
			ResultSet resultSet = database.ExecuteStatement("insert into " + typeInString + " VALUES((select count(id) + 1 from " + typeInString + "),"  + validUser.getID() + ")");
			result = true;
		}
		
		return result;
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



}
