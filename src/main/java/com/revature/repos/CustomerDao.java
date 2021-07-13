package com.revature.repos;

import com.revature.model.Customer;
import com.revature.model.User;

public interface CustomerDao {
	boolean addCustomer(User user, Customer customer);
	int getCustomerCount();
	boolean isNewUser(User user);
}
