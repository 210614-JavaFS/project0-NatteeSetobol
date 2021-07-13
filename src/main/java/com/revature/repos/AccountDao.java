package com.revature.repos;

import java.util.ArrayList;

import com.revature.model.AccountInformation;
import com.revature.model.User;

public interface AccountDao {
	int getAccountCount();
	User openAccount(User user, String type);
	ArrayList<AccountInformation>getAccountById(User user);
	float getBalance(User user, AccountInformation accountInformation);
	boolean withDraw(User user, AccountInformation accountInformation, float amountToWithDraw);
	int getAccountNumber(User user, AccountInformation accountInformation);
	float transferMoney(User user, AccountInformation accountInformation, float transferAmount,
			int accountNumber, float userBalance);
	boolean DepositToAccount(float currentBalance, int userId);
	int doesAccountExist(int account);
	

	
	
	
}
