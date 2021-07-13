package com.revature.repos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.Database;
import com.revature.model.Customer;
import com.revature.model.User;

public class CustomerDaoImp implements CustomerDao {
	private Database database  = null;
	public CustomerDaoImp()
	{
		try {
			database = Database.getInstance();
		} catch (SQLException e) {
		}
	}
	@Override
	public boolean addCustomer(User user, Customer customer) {
		boolean result = false;
		String stringQuery = null;
		PreparedStatement statement = null;
		int newId = 0;
		
		newId = getCustomerCount()+1;
		stringQuery = "insert into public.customers (uid,first,last,address,city,state,zipcode,phone_number,email,birthdate) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			
			statement = database.getConnection().prepareStatement(stringQuery);
			statement.setInt(1, user.getID());
			statement.setString(2, customer.getFirst());
			statement.setString(3, customer.getLast());
			statement.setString(4, customer.getAddress());
			statement.setString(5, customer.getCity());
			statement.setString(6, customer.getState());
			statement.setString(7, customer.getZipcode());
			statement.setString(8, customer.getPhoneNumber());
			statement.setString(9, customer.getEmail());
			statement.setString(10, customer.getDateOfBirth());
			statement.execute();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int getCustomerCount() {
		String stringQuery = null;
		PreparedStatement nextStatement = null;
		ResultSet resultSet = null;
		int customerCount = 0;

		stringQuery = "select max(id) as customercount from public.customers";
		try {
			nextStatement = database.getConnection().prepareStatement(stringQuery);
			resultSet = nextStatement.executeQuery();
			while (resultSet.next())
			{
				customerCount = resultSet.getInt("customercount");
				break;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return customerCount;
	}

	@Override
	public boolean isNewUser(User user) {
		PreparedStatement nextStatement = null;
		String stringQuery = null;
		boolean result = true;
		ResultSet resultSet = null;
		
		stringQuery = "select uid from public.customers where uid=?";
		try {
			nextStatement = database.getConnection().prepareStatement(stringQuery);
			
			nextStatement.setInt(1, user.getID());
			resultSet = nextStatement.executeQuery();
			
			while (resultSet.next())
			{
					result = false;
					break;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
