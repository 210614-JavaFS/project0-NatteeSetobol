package com.revature.controller;

import java.util.Scanner;

public class InputController {
	private static Scanner scan = new Scanner(System.in);
	
	public int getUserInput()
	{
		int result = 0;
		boolean validInput = false;
	
	//	while(validInput == false)
		{
			System.out.print("> ");
			//try
		//	{
				result = scan.nextInt();
				validInput = true;
			/*
			} catch (Exception e)
			{
				scan.nextLine();
				System.out.println("Invalid Choice!");
			}
			*/
		}
		
		return result;
	}
}
