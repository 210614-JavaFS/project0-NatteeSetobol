package com.revature.repos;

import com.revature.model.User;

public interface UserDao {

	User FindUserById(int id);
	User FindUserByUsername(String username);
	User AddNewUser(String username, String password);
	User Login(String username, String password);
}
