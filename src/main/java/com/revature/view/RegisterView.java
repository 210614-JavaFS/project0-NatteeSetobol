package com.revature.view;

import com.revature.controller.RegisterController;
import com.revature.model.User;

public class RegisterView extends View 
{
	RegisterController registerController = null;
	public static RegisterView instance;
	
	public RegisterView() 
	{
		super(2, "RegisterView", "RegisterView");
	}
	
	public User show(User user)
	{
		registerController = new RegisterController();
		boolean success = false;
		
		System.out.println("Register for a new account.");
		System.out.println("");
		
		user = new User();
		
		System.out.println("Please enter your Username:");
		user  = registerController.getUserName(user);
		System.out.println("");
		System.out.println("Please enter your User password:");
		user  = registerController.getUserPassword(user);
		System.out.println("");
		
		return user;
	}

	public static RegisterView getView()
	{
		if (instance == null)
		{
			instance = new RegisterView();
		}
		
		return instance;
	}
}
