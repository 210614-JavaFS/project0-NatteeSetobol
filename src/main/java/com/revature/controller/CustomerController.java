package com.revature.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.Database;
import com.revature.model.AccountInformation;
import com.revature.model.Customer;
import com.revature.model.User;
import com.revature.repos.AccountDaoImp;
import com.revature.repos.CustomerDaoImp;
import com.revature.repos.UserDaoImp;

public class CustomerController {

	private AccountDaoImp accountDaoImp = null;
	private CustomerDaoImp customerDaoImp = null;
	
	private static Scanner scan = new Scanner(System.in);
	Customer customer = null;
	
	public CustomerController()
	{
		accountDaoImp = new AccountDaoImp();
		customerDaoImp = new CustomerDaoImp();
	}

	public boolean isNewUser(User user) {
		boolean result = false;
		
		result = customerDaoImp.isNewUser(user);
		
		return result;
	}


	public boolean isUserApproved(User user) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean hasAnAccount(User user) {
		// TODO Auto-generated method stub
		return false;
	}


	public void inputFirstName(User user) {
		customer = new Customer();

		customer.setID(FindCustomerId(user));
		customer.setFirst(scan.nextLine());
	}


	private int FindCustomerId(User user) {
		return 0;
	}


	public void inputLastName(User user) {
		 customer.setLast(scan.nextLine());
	}


	public void getBirthdate(User user) {
		customer.setDateOfBirth(scan.nextLine());
	}


	public void inputAddress(User user) {
		customer.setAddress(scan.nextLine());
	}


	public void inputCity(User user) {
		customer.setCity(scan.nextLine());
	}


	public void inputState(User user) {
		customer.setState(scan.nextLine());
	}


	public void getPhoneNumber(User user) {
		customer.setPhoneNumber(scan.nextLine());
	}

	public void getEmail(User user) {
		customer.setEmail(scan.nextLine());
	}


	public User submit(User user) 
	{
		User newUser = null;
		UserController userController = new UserController();
		
		newUser = userController.Register(user);
		customerDaoImp.addCustomer(newUser, customer);
	
		return newUser;
	}

	public int getUserChoice() {
		
		int result = 0;
		
		System.out.print("> ");
		result = scan.nextInt();
		return result;
	}
	
	public float getUserChoicefloat() {
		
		float result = 0;
		
		System.out.print("> ");
		result = scan.nextFloat();
		return result;
	}


	public User openAccount(User user, int userAccountChoice) {
		String userAccountChoiceInString = null;
		if (userAccountChoice == 1)
		{
			userAccountChoiceInString = "Checking";
		} else {
			userAccountChoiceInString = "Saving";
		}
		user = accountDaoImp.openAccount(user, userAccountChoiceInString);
		return user;
	}

	public ArrayList<AccountInformation>getAccounts(User user) {
		
		return accountDaoImp.getAccountById(user);
	}

	public boolean checkChoice(int accountChoice, int minChoice, int maxChoice) {
		
		if (accountChoice > minChoice && accountChoice < maxChoice+1)
		{
			return true;
		}
		return false;
	}

	public float getBalance(User user, AccountInformation accountInformation) 
	{
		return accountDaoImp.getBalance(user, accountInformation);
	}

	public float withDrawlFromAccount(User user, AccountInformation accountInformation) 
	{
		boolean isValid = false;
		float result = 0;
		float currentBalance = 0.00f;
		
		currentBalance = getBalance(user,accountInformation);
		
		System.out.print("> ");
		result = scan.nextFloat();
		
		if (currentBalance > 0)
		{
			if (result > 0 &&  result < currentBalance)
			{
				withDraw(user, accountInformation, result);
				isValid = true;
			} else {
				result = 0.00f;
			}
		} else {
			result = 0.00f;
		}
		
		return result;
	}

	private boolean withDraw(User user, AccountInformation accountInformation, float amountToWithDraw) 
	{
		boolean result = false;
		
		return accountDaoImp.withDraw(user, accountInformation, amountToWithDraw);
	}

	public float despositToAccount(User user, AccountInformation accountInformation) {
		float depositAmount = 0.00f;
		float currentBalance = 0.00f;
		
		currentBalance = getBalance(user,accountInformation);
		
		System.out.print("> ");
		depositAmount = scan.nextFloat();
		
		if (depositAmount > 0)
		{
			
			currentBalance += depositAmount;
			accountDaoImp.DepositToAccount(currentBalance, user.getID());
			
		} else {
			depositAmount = 0.00f;
		}
	
		return depositAmount;
	}

	public float getTransferAmountFromUser(User user, AccountInformation accountInformation) {
		float currentBalance = 0.00f;
		float result = 0.00f;
		
		currentBalance = getBalance(user,accountInformation);
		System.out.print("> ");
		result = scan.nextFloat();
		
		if (currentBalance > 0)
		{
			if (result > 0 &&  result <= currentBalance)
			{
				
			} else {
				result = 0.00f;
			}
		} else {
			result = 0.00f;
		}
		
		return result;
	}

	public int getAccountNumber(User user, AccountInformation accountInformation) {
		
		ResultSet resultSet =  null;
		int result = 0;
		
		System.out.print("> ");
		result = scan.nextInt();
		
		if (result > 0)
		{
			result = accountDaoImp.doesAccountExist(result);
			
		}
		
		return result;
	}


	public float transferMoney(User user, AccountInformation accountInformation, float transferAmount,
		int accountNumber) {
		float newBalance = 0.0f;
		float  currentBalance = 0.0f;
		float transfered = 0.00f;
		
		currentBalance = getBalance(user,accountInformation);
		
		newBalance = currentBalance - transferAmount;
		if (newBalance >= 0)
		{
			transfered = accountDaoImp.transferMoney(user, accountInformation , transferAmount,
					accountNumber, newBalance);

		}
		return transfered;
	}

	public void inputZipcode(User user) {
		// TODO Auto-generated method stub
		System.out.print("> ");
		customer.setZipcode(scan.nextLine());
	}


}
