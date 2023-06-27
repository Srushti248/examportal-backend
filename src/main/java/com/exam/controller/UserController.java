package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

import helper.UserFoundException;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@GetMapping("/test")
	public String test(){
		return "welcome to the backend api of Examportal";
	}
//	
	// Creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		
		
		// BcryptPassword
		
	user.setPassword(this.bcryptPasswordEncoder.encode(user.getPassword()));
		
		 Set<UserRole> roles = new HashSet<>();
		 
		 Role role = new Role();
		 role.setRoleId(44);
		 role.setRoleName("Normal");
		 
		 UserRole userRole = new UserRole();
		 
		 userRole.setRole(role);
		 userRole.setUser(user);
		 
		 roles.add(userRole);
		 
		return this.userService.createUser(user,roles);
		
	}
	
	 //getting user
		@GetMapping("/{username}")
		public User getUser(@PathVariable("username")String username) {
			
			return this.userService.getUser(username);
			
		}
			
	// Delete user by id
			
			@DeleteMapping("/{userId}")
			public void deleteUser(@PathVariable("userId")Integer userId) {
				
				this.userService.deleteUser(userId);
			
		}
			
	//update the user	ytrtt
			
//			@PutMapping("/{user}")
//			public User updateUser(@PathVariable("user")String user) {
				
//			}
			 @ExceptionHandler(UserFoundException.class)
			    public ResponseEntity<?> exceptionHandler(UserFoundException ex)
			    {
			        return ResponseEntity.ok("User is already there");
			    }
			}
		
	

