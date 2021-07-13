package com.revature.repos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.Database;
import com.revature.model.User;

public class UserDaoImp implements UserDao {

	private Database database  = null;
	public UserDaoImp()
	{
		try {
			database = Database.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}
	@Override
	public User FindUserById(int id) {
		String stringQuery = null;
		User result = null;
		
		stringQuery = "select * from public.users where id=?";
		
		try {
			PreparedStatement statement = database.getConnection().prepareStatement(stringQuery);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next())
			{
				result = new User();
				result.setID(resultSet.getInt(id));
				result.setPassword(result.getPassword());
				result.setUsername(result.getUsername());
				break;
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public User FindUserByUsername(String username) {
		String stringQuery = null;
		User result = null;
		
		stringQuery = "select * from public.users where username=?";
		
		try {
			PreparedStatement statement = database.getConnection().prepareStatement(stringQuery);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next())
			{
				result = new User();
				result.setID(resultSet.getInt(result.getID()));
				result.setPassword(result.getPassword());
				result.setUsername(result.getUsername());
				break;
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public User AddNewUser(String username, String password) {
		User newUser = null;
		String stringQuery = null;
		PreparedStatement statement  = null;
		PreparedStatement nextStatement = null;
		ResultSet resultSet = null;
		int newUserId = 0;
	
		stringQuery =  "insert into public.users (username, password) VALUES(?,?)";
		
		try {
			statement = database.getConnection().prepareStatement(stringQuery);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.execute();
			newUser = new User();
			newUser.setUsername(username);
			newUser.setPassword(password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		stringQuery = "SELECT id as usercount FROM users ORDER BY id DESC LIMIT 1";
		try {
			nextStatement = database.getConnection().prepareStatement(stringQuery);
			resultSet = nextStatement.executeQuery();
			while (resultSet.next())
			{
				newUserId = resultSet.getInt("usercount");
				break;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		newUser.setID(newUserId);
		return newUser;
	}
	@Override
	public User Login(String username, String password) {
		String stringQuery = null;
		PreparedStatement statement  = null;
		ResultSet resultSet = null;
		User loginUser = null;
		
		stringQuery = "select * from public.users where username = ? and password = ?";
		
		try {
			statement = database.getConnection().prepareStatement(stringQuery);
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				loginUser = new User();
				loginUser.setID(resultSet.getInt("id"));
				loginUser.setUsername(resultSet.getString("username"));
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginUser;
	}

}
