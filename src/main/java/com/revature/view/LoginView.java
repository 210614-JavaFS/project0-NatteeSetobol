package com.revature.view;

import com.revature.controller.LoginController;
import com.revature.model.User;

public class LoginView extends View {
	public static LoginView instance;
	public LoginController loginController;
	public LoginView() {
		super(3, "LoginView", "Login");
		loginController = new LoginController();
		// TODO Auto-generated constructor stub
	}
	
	public User show(User user)
	{
	
		System.out.println("Please Login with your username and password");
		System.out.println("");
		System.out.println("Please enter you Username");
		user = loginController.inputUserName(user);
		System.out.println("");
		System.out.println("Please enter you Password");
		user = loginController.inputUserPassword(user);
		user = loginController.login(user);
		
		if (user == null)
		{
			System.out.println("You entered the wrong username and password!");
		}

		return user;
	}
	public static LoginView getView()
	{
		if (instance == null)
		{
			instance = new LoginView();
		}
		
		return instance;
	}
}
