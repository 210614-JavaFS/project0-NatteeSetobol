package com.revature.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.revature.model.User;
import com.revature.repos.AdminDaoImp;

public class AdminController {
	private static Scanner scan = new Scanner(System.in);
	private AdminDaoImp adminDaoImp = null;

	public AdminController()
	{
		adminDaoImp = new AdminDaoImp();
	}
	
	public boolean isAdmin(User user)
	{
		return adminDaoImp.isAdmin(user);
	}
	
	public boolean isEmployee(User user)
	{		
		return adminDaoImp.isEmployee(user);
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

		return adminDaoImp.getAccountToApprove(user);
	}


	public void approveAccount(HashMap<String, Object> currentMap) {
		adminDaoImp.approveAccount(currentMap);
	}

	public void disapproveAccount(HashMap<String, Object> currentMap) 
	{
		adminDaoImp.disapproveAccount(currentMap);
	}


}
