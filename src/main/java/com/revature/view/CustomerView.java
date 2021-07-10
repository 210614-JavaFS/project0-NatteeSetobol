package com.revature.view;

import java.util.ArrayList;

import com.revature.controller.CustomerController;
import com.revature.model.AccountInformation;
import com.revature.model.User;

public class CustomerView extends View {
	
	public static CustomerView instance = null;
	private static CustomerController customerController = null;

	public CustomerView() {
		super(5, "Customer", "Customer View");
		customerController = new CustomerController();

	}

	public static CustomerView getView()
	{
		if (instance == null)
		{
			instance = new CustomerView();
		}
		
		return instance;
	}
	
	public User display(User user)
	{

				if (customerController.isNewUser(user))
				{
					System.out.println("Welcome! Before we get started we need your information!");
					System.out.println("");
					System.out.println("Please tell us your first name:");
					customerController.inputFirstName(user);
					System.out.println("Please tell us your last name:");
					customerController.inputLastName(user);
					System.out.println("Please tell us your birthdate: ");
					customerController.getBirthdate(user);
					System.out.println("Please tell us your address:");
					customerController.inputAddress(user);
					
					System.out.println("Please tell us the city you live in:");
					customerController.inputCity(user);
					System.out.println("Please tell us the state you live in:");
					customerController.inputState(user);
					System.out.println("Please tell us your Phone number: ");
					customerController.getPhoneNumber(user);
					System.out.println("Please tell us your E-Mail: ");
					customerController.getEmail(user);
					customerController.submit(user);
				} else {
					int userChoice = 0;
					System.out.println("");
					System.out.println("What do you want to do?");
					System.out.println("");
					System.out.println("1. Open an Account");
					System.out.println("2. Withdraw from Account");
					System.out.println("3. Depost from Account");
					System.out.println("4. Transfer money to an Account");
					System.out.println("5. Logoff");
					System.out.println("");
					userChoice = customerController.getUserChoice();
					
					switch(userChoice)
					{
						case 1:
						{
							int userAccountChoice = 0;
							
							System.out.println("");
							System.out.println("What kind of account do you want to open?");
							System.out.println("");
							System.out.println("1. Checking Account");
							System.out.println("2. Saving Account");
							System.out.println("3. Go Back");
							userAccountChoice = customerController.getUserChoice();
							
							switch(userAccountChoice)
							{
								case 1:
								case 2:
								{
									user = customerController.openAccount(user,userAccountChoice);
									System.out.println("");
									System.out.println("you have opened an account, however, you account must be approve by an employee or admin.");
									System.out.println("");
									break;
								}
								default:
								{
									break;
								}
							}
							
							break;
						}
						case 2:
						{
							
							int accountChoice = 0;
							ArrayList<AccountInformation> accountInformationArray = customerController.getAccounts(user);
							System.out.println("");
							System.out.println("Please choose an account:");
							System.out.println("");
							
							System.out.printf("%-22s%-22s%-22s%-22s\n","#","Account Number","Type", "Balance");
							for (int accountIndex = 0; accountIndex < accountInformationArray.size();accountIndex++)
							{
								AccountInformation  accountInformation = accountInformationArray.get(accountIndex);
								System.out.printf("%-22d%-22s%-22s%-22.02f\n",accountIndex+1,accountInformation.getNumber() , accountInformation.getAccounttype(),accountInformation.getBalance());
								
							}
							
							accountChoice = customerController.getUserChoice();
							
							if (customerController.checkChoice(accountChoice, 0,accountInformationArray.size()))
							{
								AccountInformation  accountInformation = accountInformationArray.get(accountChoice-1);
								System.out.println("");
								System.out.println("How much do you want to withdraw?");
								System.out.println("");
								float balance = customerController.getBalance(user, accountInformation);
								System.out.printf("Current balance: %.02f", balance);
								System.out.println("");
								
								float totalAmountWithdrawn = customerController.withDrawlFromAccount(user,accountInformation);
								
								System.out.printf("You withdrawl: %.02f",totalAmountWithdrawn );
								
							}
							
							break;
						}
						case 3:
						{
							float totalTransfered = 0.00f;
							int accountChoice = 0;
							ArrayList<AccountInformation> accountInformationArray = customerController.getAccounts(user);
							System.out.println("");
							System.out.println("Please choose an account to desposit to:");
							System.out.println("");
							
							System.out.printf("%-22s%-22s%-22s%-22s\n","#","Account Number","Type", "Balance");
							for (int accountIndex = 0; accountIndex < accountInformationArray.size();accountIndex++)
							{
								AccountInformation  accountInformation = accountInformationArray.get(accountIndex);
								System.out.printf("%-22d%-22s%-22s%-220.2f\n",accountIndex+1,accountInformation.getNumber() , accountInformation.getAccounttype(),accountInformation.getBalance());
								
							}
							
							
							accountChoice = customerController.getUserChoice();
							
							if (customerController.checkChoice(accountChoice, 0,accountInformationArray.size()))
							{
								AccountInformation  accountInformation = accountInformationArray.get(accountChoice-1);
								totalTransfered = customerController.despositToAccount(user,accountInformation);
							}
							
								
							System.out.printf("You deposit: %.02f",totalTransfered );
							break;
						}
						case 4:
						{
							int accountChoice = 0;
							ArrayList<AccountInformation> accountInformationArray = customerController.getAccounts(user);
							System.out.println("");
							System.out.println("Please choose an account to transfer money to:");
							System.out.println("");
							
							System.out.printf("%-22s%-22s%-22s%-22s\n","#","Account Number","Type", "Balance");
							for (int accountIndex = 0; accountIndex < accountInformationArray.size();accountIndex++)
							{
								AccountInformation  accountInformation = accountInformationArray.get(accountIndex);
								System.out.printf("%-22d%-22s%-22s%-220.2f\n",accountIndex+1,accountInformation.getNumber() , accountInformation.getAccounttype(),accountInformation.getBalance());
								
							}
							
							accountChoice = customerController.getUserChoice();
							
							if (customerController.checkChoice(accountChoice, 0,accountInformationArray.size()))
							{
								float transferAmount = 0.00f;
								float amountTransfered = 0.00f;
								int accountNumber = 0;
								
								AccountInformation  accountInformation = accountInformationArray.get(accountChoice-1);
								System.out.println("");
								System.out.println("How much to you want to transfer?:");
								System.out.println("");
								transferAmount = customerController.getTransferAmountFromUser(user,accountInformation);
								System.out.println("");
								System.out.println("Please the account number you want to transfer the money to.");
								System.out.println("");							
								accountNumber = customerController.getAccountNumber(user, accountInformation);
								if (accountNumber > 0)
								{
									amountTransfered = customerController.transferMoney(user, accountInformation, transferAmount, accountNumber);
									System.out.printf("You have transfered $ %.2f to %d\n",amountTransfered, accountNumber);
								} else {
									System.out.println("Account number not found.");
								}
																
							}
							break;
						}
						case 5:
						{
							user = null;
							break;
						}
					}
					
				}
			
	
		return user;
	}
}
