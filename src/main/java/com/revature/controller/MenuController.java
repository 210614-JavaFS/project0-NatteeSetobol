package com.revature.controller;

import com.revature.ViewFactory;
import com.revature.model.User;
import com.revature.view.LoginView;
import com.revature.view.RegisterView;

public class MenuController {
	public enum Menu { NONE,REGISTER,LOGIN, EXIT};
	
	public static InputController inputController = new InputController();
	private static RegisterView registerView = null;
	private static LoginView loginView = null;
	
	public static User ValidateInput(User user)
	{
		boolean exit = false;
		Menu menu = Menu.NONE;
		int userInput = 0;
		
		userInput = inputController.getUserInput();
		
		try {
			menu = Menu.values()[userInput];
		} catch (Exception e)
		{
			System.out.println("Invalid choice!");
		}
	
			switch(menu)
			{
				case REGISTER:
				{
					registerView = (RegisterView) ViewFactory.showView("register");
					user = registerView.show(user);
					exit=true;
					break;
				}
				case LOGIN:
				{
					loginView = (LoginView) ViewFactory.showView("login");
					user = loginView.show(user);
					break;
				}
				case EXIT:
				{
					exit = true;
					break;
				}
			}
		
		return user;
	}
	
	
	
}
