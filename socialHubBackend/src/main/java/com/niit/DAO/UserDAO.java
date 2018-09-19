package com.niit.DAO;

import com.niit.models.User;

public interface UserDAO {
	
	public boolean registration(User user);
	public boolean isEmailUnique(String email);
	public User login(User user);
	public boolean updateUser(User user);
	public User getUser(String email);
}
