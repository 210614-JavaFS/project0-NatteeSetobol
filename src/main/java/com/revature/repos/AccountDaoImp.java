package com.revature.repos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Database;
import com.revature.model.AccountInformation;
import com.revature.model.User;

public class AccountDaoImp implements AccountDao {
	private Database database  = null;
	public AccountDaoImp()
	{
		try {
			database = Database.getInstance();
		} catch (SQLException e) {
		}
	}
	
	@Override
	public User openAccount(User user, String type) {
		boolean result = false;
		String stringQuery = null;
		PreparedStatement statement = null;
		
		
		stringQuery = "insert into public.accounts (uid,type,number,approved, balance) VALUES(?,?,?,?,?)";
		try {
			statement = database.getConnection().prepareStatement(stringQuery);
			statement.setInt(1, user.getID());
			statement.setString(2, type);
			statement.setInt(3, 12345);
			statement.setBoolean(4, false);
			statement.setFloat(5, 0.00f);
			statement.execute();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return user;
		
	}

	@Override
	public ArrayList<AccountInformation> getAccountById(User user) {
		// TODO Auto-generated method stub
		int userId = 0;
		ResultSet resultSet = database.ExecuteStatement("select * from public.accounts");
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

	@Override
	public float getBalance(User user, AccountInformation accountInformation) {
		float result = 0.00f;
		ResultSet resultSet = database.ExecuteStatement("select * from public.accounts");
		
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


	@Override
	public boolean withDraw(User user, AccountInformation accountInformation, float amountToWithDraw) {
		// TODO Auto-generated method stub
		boolean result = false;
		float currentBalance = 0.00f;
		String stringQuery = null;
		PreparedStatement statement = null;
		
		currentBalance = getBalance(user,accountInformation);
		
		currentBalance -= amountToWithDraw;
		stringQuery = "update accounts set balance = ? where uid = ?";
		try {
			statement = database.getConnection().prepareStatement(stringQuery);
			statement.setFloat(1, currentBalance);
			statement.setInt(2, user.getID());
			statement.execute();
			result = true;
		} catch(Exception e)
		{
				
		}
		return result;
	}

	@Override
	public int getAccountNumber(User user, AccountInformation accountInformation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float transferMoney(User user, AccountInformation accountInformation, float transferAmount,
			int accountNumber, float userBalance) {
		ResultSet resultSet =  null;
		float transfered = 0.00f;
		String accountQuery = null;
		PreparedStatement accountPrep = null;
		
		accountQuery = "select * from public.accounts";
		
		try {
			accountPrep = database.getConnection().prepareStatement(accountQuery);
			resultSet = accountPrep.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			while (resultSet.next())
			{
				if (resultSet.getInt("number") == accountNumber)
				{
					float balance = resultSet.getFloat("balance");
					ResultSet newSet = null;
					ResultSet oldSet = null;
					PreparedStatement userPrepStatement = null;
					PreparedStatement transeePrepStatement = null;
					String transeeQuery= null;
					String userQuery= null;
					
					balance += transferAmount;
					
					transeeQuery ="update public.accounts set balance = ? where number = ?";
					transeePrepStatement = database.getConnection().prepareStatement(transeeQuery);
					transeePrepStatement.setFloat(1, balance);
					transeePrepStatement.setInt(2, accountNumber);
					transeePrepStatement.execute();
					
					userQuery = "update public.accounts set balance = ? where uid = ?";
					userPrepStatement = database.getConnection().prepareStatement(userQuery);
					userPrepStatement.setFloat(1, userBalance);
					userPrepStatement.setInt(2, user.getID());
					userPrepStatement.execute();
					
					transfered = transferAmount;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transfered;
	}

	@Override
	public int getAccountCount() {
		String stringQuery = null;
		PreparedStatement nextStatement = null;
		ResultSet resultSet = null;
		int accountCount = 0;

		stringQuery = "select count(*) as count from public.accounts";
		try {
			nextStatement = database.getConnection().prepareStatement(stringQuery);
			resultSet = nextStatement.executeQuery();
			while (resultSet.next())
			{
				accountCount = resultSet.getInt("count");
				break;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return accountCount;
	
	}

	@Override
	public boolean DepositToAccount(float currentBalance, int userId) {
		String stringQuery=null;
		PreparedStatement prepStatement = null;
		boolean result = false;
		
		stringQuery = "update accounts set balance = ? where uid= ?";
		try {
			prepStatement = database.getConnection().prepareStatement(stringQuery);
			prepStatement.setFloat(1, currentBalance);
			prepStatement.setInt(2, userId);
		
			result = prepStatement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int doesAccountExist(int account) {
			ResultSet resultSet = null;
			int result = 0;
			
			resultSet = database.ExecuteStatement("select * from public.accounts");
			try {
				boolean found = false;
				while(resultSet.next())
				{
					if (resultSet.getInt("number") == account)
					{
						result = account;
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
		return result;
	}

}
