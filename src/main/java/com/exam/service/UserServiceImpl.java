package com.exam.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.UserRepository;
import com.exam.repo.UserRoleRepository;

import helper.UserFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	

	@Override
	public User createUser(User user, Set<UserRole> userRoles)throws Exception {
		// TODO Auto-generated method stub
		
		
		User local =this.userRepository.findByUsername(user.getUsername());
		
		if(local != null) {
			System.out.println("Student Is Already Exists !!");
			throw new UserFoundException();
		}
		
		else {
//			Create user
			
		for (UserRole sr : userRoles)
		{
			userRoleRepository.save(sr.getRole());
		}
		
		user.getUserRoles().addAll(userRoles);
		 local = this.userRepository.save(user);
		}
		
		return local;
	}

  // Getting user by username
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(userId);
		
	}

//	@Override
//	public User updateUser(String user) {
		// TODO Auto-generated method stub
//		return null;
	}
	


	


