package com.revature;

import com.revature.controller.AdminController;
import com.revature.controller.EmployeeController;
import com.revature.model.User;
import com.revature.view.AdminView;
import com.revature.view.CustomerView;
import com.revature.view.EmployeeView;
import com.revature.view.MenuView;

public class Driver {
	private static boolean programEnd=false;
	private static AdminController adminController = new AdminController();
	
	public static void main(String[] args)
	{
		User user = null;
		
		while(programEnd == false)
		{
			if (user == null)
			{
				MenuView menuView = (MenuView) ViewFactory.showView("MainMenu");
				user = menuView.display(user);
			} else {
				if (IsAdmin(user))
				{
					AdminView adminView = (AdminView) ViewFactory.showView("Admin");
					adminView.display(user);
					System.out.println("welcome admin");
				} else 
				if (IsEmployee(user))
				{
					EmployeeView employeeView = (EmployeeView) ViewFactory.showView("Employee");
					user = employeeView.display(user);
				} else 
				{
					CustomerView customView = (CustomerView) ViewFactory.showView("Customer");
					user = customView.display(user);
				}
			}
		}
	}

	private static boolean IsEmployee(User user) {
		// TODO():
		// 1. Check if user is in the employee table
		boolean result = false;
		
		EmployeeController employeeController = new EmployeeController();
		
		result = employeeController.isEmployee(user);
		
			
		
		
		return result;
	}

	private static boolean IsAdmin(User user) {
		boolean result = false;
		if (adminController.isAdmin(user))
		{
				result = true;
		}
		return result;
	}
}