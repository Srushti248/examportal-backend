package com.exam.service;

import java.util.Set;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService  {
	
	public User createUser (User user, Set<UserRole> userRoles) throws Exception;
	
	//getting user by username
	
	public User getUser(String username);
	
	// delete user by id
	
	public void deleteUser( Integer userId);
	
	// update the user
	
//	public User updateUser(String user);
		
	


}
