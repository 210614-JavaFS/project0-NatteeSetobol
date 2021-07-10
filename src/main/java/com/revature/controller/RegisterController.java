package com.revature.controller;

import java.util.Scanner;

import com.revature.model.User;

public class RegisterController {
	private static Scanner scan = new Scanner(System.in);
	private static UserController userController = new UserController();
	
	public RegisterController()
	{
	
	}
	
	public User getUserName(User user)
	{
		boolean result = true;
		
		String Username = scan.nextLine();
		
		if (Username.length() < 1)
		{
			result = false;
		} else 
		{
			user.setUsername(Username);
		}
		return user;
	}

	public User getUserPassword(User user) {
		boolean result = true;
		
		String userPassword = scan.nextLine();
		
		if (userPassword.length() > 1)
		{
			user.setPassword(userPassword);
		}
		return user;
	}

	public User Register(User user) {
		System.out.println("");
		System.out.println("You have registered an account");
		System.out.println("");
		//if (user != null)
		//{
		//	userController.Register(user);
		//}
		return user;
	}
	
}
