package com.revature.repos;

import java.util.ArrayList;
import java.util.HashMap;

import com.revature.model.User;

public interface AdminDao {
	boolean isAdmin(User user);
	//boolean isEmployee(User user);

}
