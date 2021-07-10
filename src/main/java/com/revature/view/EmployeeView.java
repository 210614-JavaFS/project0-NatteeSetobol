package com.revature.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.revature.controller.EmployeeController;
import com.revature.model.AccountInformation;
import com.revature.model.Customer;
import com.revature.model.User;

public class EmployeeView  extends View {
	public static EmployeeView instance;
	public static EmployeeController employeeController = null;
	
	public EmployeeView(int viewID, String name, String displayName) {
		super(viewID, name, displayName);
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeView()
	{
		super(10, "EmployeeView", "Employee View");
		employeeController = new EmployeeController();
	}

	public static EmployeeView getView()
	{
		if (instance == null)
		{
			instance = new EmployeeView();
		}
		
		return instance;
	}

	public User display(User user) {
		int employeeChoice = 0;
		System.out.println("");
		System.out.println("What do you weant to do?");
		System.out.println("1. Approve/Deny Account.");
		System.out.println("2. View user's personal Infomation");
		System.out.println("3. View Account Information");
		System.out.println("4. Logoff");
		System.out.println(""); 
		
		employeeChoice = employeeController.getEmployeeInput(user, 1,4);
		
		switch(employeeChoice)
		{
			case 1:
			{
				int approveNumber = 0;
				ArrayList<HashMap<String, Object>> userToApprove = null;
				userToApprove = employeeController.getAccountToApprove(user);
				
				System.out.println("");
				System.out.println("Account(s) to approve:");
				if (userToApprove.size() == 0)
				{
					System.out.println("");
					System.out.println("There are no Account to Approve");
					System.out.println("");
				} else {
					System.out.println("");
					for (int userIndex=0; userIndex < userToApprove.size();userIndex++)
					{
						HashMap<String, Object> currentMap = userToApprove.get(userIndex);
						User currentUser = (User) currentMap.get("user");
						AccountInformation Account = (AccountInformation) currentMap.get("account");
						System.out.printf("%-22s%-22s%-22s%-22s\n","#","User","Account Number","Type");
						System.out.printf("%-22d%-22s%-22d%-22s\n",userIndex+1,currentUser.getUsername(),Account.getNumber(),Account.getAccounttype());
					}
					System.out.println("");
					System.out.println( (userToApprove.size()+1) + ". Go Back");
					
					System.out.println("");
					approveNumber = employeeController.Validate(user);
					
					if (approveNumber > 0 && approveNumber <= userToApprove.size())
					{
						int accountType = 0;
						System.out.println("");
						System.out.println("Approve or Deny?:");
						System.out.println("");
						System.out.println("1. Approve");
						System.out.println("2. Deny");
						System.out.println("3. Cancel");
						try
						{
							accountType = employeeController.Validate(user);
						} catch(Exception e)
						{
							//isInMenu = true;
							break;
						}

						if (accountType < 3 && accountType > 0)
						{
							if (userToApprove != null)
							{
								if (accountType == 1)
								{
									HashMap<String, Object> currentMap = userToApprove.get(approveNumber-1);
									employeeController.approveAccount(currentMap);
								} else 
								if (accountType == 2)
								{
									HashMap<String, Object> currentMap = userToApprove.get(approveNumber-1);
									employeeController.disapproveAccount(currentMap);
								}
							}
						} 
					}
				}
				
				break;
			}
			case 2:
			{
				String username = null;
				User validUser = null;
				System.out.println("");
				System.out.println("Please enter a username:");
				System.out.println("");
				validUser = employeeController.ValidateUser(user);
				
				if (validUser != null)
				{
					Customer customerInfo  = null;
					customerInfo = employeeController.getUsernameInformation(validUser, username);
					
					System.out.println("");
					System.out.println("User's ID: " + validUser.getID());
					System.out.println("Customer's ID: " + customerInfo.getID());
					System.out.println("Username: " + validUser.getUsername());
					System.out.println("Firstname: " + customerInfo.getFirst());
					System.out.println("Lastname: " + customerInfo.getLast());
					System.out.println("Address: " + customerInfo.getAddress());
					System.out.println("City: " + customerInfo.getCity());
					System.out.println("State: " + customerInfo.getState());
					System.out.println("Phone Number: " + customerInfo.getPhoneNumber());
					System.out.println("E-mail Number: " + customerInfo.getEmail());
					System.out.println("Date of Birth: " + customerInfo.getDateOfBirth());
					System.out.println("");
				}
				
				break;
			}
			case 4:
			{
				user = null;
				break;
			}
		}
		
		
		return user;
	}

}
