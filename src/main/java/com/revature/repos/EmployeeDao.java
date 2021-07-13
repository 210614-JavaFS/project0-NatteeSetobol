package com.revature.repos;

import java.util.ArrayList;
import java.util.HashMap;

import com.revature.model.Customer;
import com.revature.model.User;

public interface EmployeeDao {
	boolean isEmployee(User user);
	ArrayList<HashMap<String, Object>> getAccountToApprove(User user);
	void approveAccount(HashMap<String, Object> currentMap);
	void disapproveAccount(HashMap<String, Object> currentMap);
	Customer getUsernameInformation(User user, String username);
	User getUserByUsername(String choice);
}
