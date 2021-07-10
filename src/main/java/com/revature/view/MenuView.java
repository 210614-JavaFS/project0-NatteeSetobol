package com.revature.view;

import com.revature.controller.MenuController;
import com.revature.model.User;

public class MenuView extends View {
	
	public static MenuView instance;
	
	public MenuView() {
		super(1, "MenuView", "Menu");
	}

	public enum Menu { NONE,REGISTER,LOGIN, EXIT};
	
	MenuController menuController = new MenuController();
	
	public User display(User user)
	{
		boolean exit = false;
		
		//while (!exit)
		{
			System.out.println("Welcome to Nuclear Power Bank");
			System.out.println("");
			System.out.println("What would you like to do?");
			System.out.println("1. Register for an account.");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.println("");
			
			user = MenuController.ValidateInput(user);	
		}
		
		return user;

	}
	
	public static MenuView getView()
	{
		if (instance == null)
		{
			instance = new MenuView();
			
		}
		
		return instance;
	}
}
