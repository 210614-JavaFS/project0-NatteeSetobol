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
import com.revature.repos.EmployeeDao;
import com.revature.repos.EmployeeDaoImp;

public class EmployeeController {
	private static Scanner scan = new Scanner(System.in);
	private Database database;
	private EmployeeDaoImp employeeDao = null;

	public EmployeeController() {
		super();
		employeeDao = new EmployeeDaoImp();
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

		return employeeDao.getAccountToApprove(user);
	}

	public boolean isEmployee(User user) {
		return employeeDao.isEmployee(user);
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
		
		resultUser = employeeDao.getUserByUsername(choice);		
		
		return resultUser;
	}

	public void approveAccount(HashMap<String, Object> currentMap) {
		employeeDao.approveAccount(currentMap);
	}

	public void disapproveAccount(HashMap<String, Object> currentMap) 
	{
		employeeDao.disapproveAccount(currentMap);
	}

	public Customer getUsernameInformation(User user, String username) {

		return employeeDao.getUsernameInformation(user, username);
	}
}
