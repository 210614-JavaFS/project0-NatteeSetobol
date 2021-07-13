package com.revature.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.revature.controller.AdminController;
import com.revature.model.AccountInformation;
import com.revature.model.User;

public class AdminView extends View{
	public static AdminView instance;
	
	public static AdminController adminController=null;

	public AdminView() {
		super(7, "admin", "admin");
		adminController = new AdminController();
		// TODO Auto-generated constructor stub
	}
	public static User display(User user)
	{
		int menuItem = 0;
		ArrayList<HashMap<String, Object>> userToApprove = null;
		
		while (menuItem != 4)
		{
			
			boolean isInMenu = false;
			switch (menuItem)
			{
				case 0:
				{
					System.out.println("Admin Panel");
					System.out.println("1. Approve/Deny accounts");
					System.out.println("2. Withdraw/Transfer/Despoit Accounts");
					System.out.println("3. Cancel an account");
					System.out.println("4. Log off");
					break;
				}
				case 1:
				{
					int approveNumber = 0;
					userToApprove = adminController.getAccountToApprove(user);
					
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
						
					}
					
					System.out.println( (userToApprove.size()+1) + ". Go Back");
					
					System.out.println("");
					approveNumber = adminController.Validate(user);
					
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
							accountType = adminController.Validate(user);
						} catch(Exception e)
						{
							isInMenu = true;
							break;
						}

						if (accountType < 3 && accountType > 0)
						{
								if (userToApprove != null)
								{
									if (accountType == 1)
									{
										HashMap<String, Object> currentMap = userToApprove.get(approveNumber-1);
										adminController.approveAccount(currentMap);
									} else 
									if (accountType == 2)
									{
										HashMap<String, Object> currentMap = userToApprove.get(approveNumber-1);
										adminController.disapproveAccount(currentMap);
									}
								}
						} else {
							isInMenu = true;
							break;
						}
						
						try
						{
							//User validUser = userToApprove.get(approveNumber-1);
							//adminController.approveUser(validUser, accountType);
							
						} catch (Exception e)
						{
							
							isInMenu = true;
							break;
						}
						
					} else {
						menuItem = 0;
					}
					
					isInMenu = true;
					break;
				}
				case 4:
				{
					user = null;
					isInMenu = true;
					break;
				}
			}
			
			if (isInMenu == false)
			{
				menuItem = adminController.Validate(user);
			}
		}
		return user;
	}
	public static AdminView getView()
	{
		if (instance == null)
		{
			instance = new AdminView();
		}
		
		return instance;
	}
}
