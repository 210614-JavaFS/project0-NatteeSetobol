package com.revature.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.Database;
import com.revature.model.AccountInformation;
import com.revature.model.Customer;
import com.revature.model.User;

public class CustomerController {

	private static Scanner scan = new Scanner(System.in);
	Customer customer = null;
	private Database database;
	
	public CustomerController()
	{
		try {
			database = Database.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isNewUser(User user) {
		//TODO():
		//Look up customers in the customer table
		//if customers.getIsNew() == true then return true
		// else return fasle
		boolean result = true;
		ResultSet resultSet = database.ExecuteStatement("select userid from public.customer");
		
		try {
			while (resultSet.next())
			{
				int customerID = resultSet.getInt("userid");
				if (customerID == user.getID())
				{
					result = false;
					break;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

		// TODO Auto-generated method stub
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


	public void submit(User user) 
	{
		//if (customer != null)
		{
			UserController userController = new UserController();
			userController.Register(user);
			
			ResultSet resultSet = database.ExecuteStatement("insert into public.customer VALUES( (select count(id)+1 from public.customer)," +
																								"" + user.getID() + "," +
																								"'" + customer.getFirst() + "'," +
																								"'"	+ customer.getLast() +  "'," + 
																								"'"	+ customer.getAddress() +  "'," + 
																								"'"	+ customer.getCity() +  "'," + 
																								"'"	+ customer.getState() +  "'," + 
																								"'"	+ customer.getPhoneNumber() +  "'," + 
																								"'"	+ customer.getEmail() +  "'" + 
																								"'"	+ customer.getDateOfBirth() +  "'" +
					");");
			
			
			
		}
	}

	public int getUserChoice() {
		
		int result = 0;
		
		System.out.print("> ");
		result = scan.nextInt();
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
		database.ExecuteStatement("insert into public.account (uid,type,number,approved,balance) VALUES("
				+ user.getID() + 
				",'" + userAccountChoiceInString + "'," 
				+ 12345 + 
				",false,0.00"
				+ ")");
		return user;
	}

	public ArrayList<AccountInformation>getAccounts(User user) {
		int userId = 0;
		ResultSet resultSet = database.ExecuteStatement("select * from public.account");
		ArrayList<AccountInformation> accounts = new ArrayList<AccountInformation>();
		try {
			while (resultSet.next())
			{
				userId = resultSet.getInt("uid");
				if (userId == user.getID())
				{
					AccountInformation accountToAdd = new AccountInformation();
					accountToAdd.setId(resultSet.getInt("id"));
					accountToAdd.setAccounttype(resultSet.getString("type"));
					accountToAdd.setBalance(resultSet.getFloat("balance"));
					accountToAdd.setNumber(resultSet.getInt("number"));
					accountToAdd.setApproved(resultSet.getBoolean("approved"));
					accountToAdd.setUid(resultSet.getInt("uid"));
					accounts.add(accountToAdd);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accounts;
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
		float result = 0.00f;
		ResultSet resultSet = database.ExecuteStatement("select * from public.account");
		
		try {
			while (resultSet.next())
			{
				int userId = resultSet.getInt("uid");
				int accountNumber = resultSet.getInt("number");
				
				if (userId == user.getID() && accountNumber == accountInformation.getNumber())
				{
					result = resultSet.getFloat("balance");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
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
		float currentBalance = 0.00f;
		
		currentBalance = getBalance(user,accountInformation);
		
		currentBalance -= amountToWithDraw;
		try {
			database.ExecuteStatement("update account set balance = " + currentBalance + " where uid=" + user.getID());
		} catch(Exception e) {
			
		}
		return result;
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
			database.ExecuteStatement("update account set balance = " + currentBalance + " where uid=" + user.getID());
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
			if (result > 0 &&  result < currentBalance)
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
			resultSet = database.ExecuteStatement("select * from public.account");
			try {
				boolean found = false;
				while(resultSet.next())
				{
					if (resultSet.getInt("number") == result)
					{
						found = true;
						break;
					}
				}
				
				if (found == false)
				{
					result = 0;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
		if (newBalance > 0)
		{
			ResultSet resultSet =  null;
			resultSet = database.ExecuteStatement("select * from public.account");
			
			try {
				while (resultSet.next())
				{
					if (resultSet.getInt("number") == accountNumber)
					{
						float balance = resultSet.getFloat("balance");
						ResultSet newSet = null;
						ResultSet oldSet = null;
						
						balance += transferAmount;
						newSet = database.ExecuteStatement("update public.account set balance = "  + balance + " where number=" + accountNumber);
						oldSet = database.ExecuteStatement("update public.account set balance = "  + newBalance + " where uid=" + user.getID());
						
						transfered = transferAmount;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return transfered;
	}


}
