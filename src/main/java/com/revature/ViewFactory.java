package com.revature;

import com.revature.view.AdminView;
import com.revature.view.CustomerView;
import com.revature.view.EmployeeView;
import com.revature.view.LoginView;
import com.revature.view.MenuView;
import com.revature.view.RegisterView;
import com.revature.view.View;

public class ViewFactory {
	public static View showView(String view)
	{
		switch (view.toLowerCase())
		{
			case "mainmenu":
			{
				return MenuView.getView();
			}
			case "register":
			{
				return RegisterView.getView();
			}
			case "login":
			{
				return LoginView.getView();
			}
			case "customer":
			{
				return CustomerView.getView();
			}
			case "admin":
			{
				return AdminView.getView();
			}
			case "employee":
			{
				return EmployeeView.getView();
			}
		}
		return null;
	}
}
