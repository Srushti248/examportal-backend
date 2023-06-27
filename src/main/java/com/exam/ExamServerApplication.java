package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamServerApplication implements CommandLineRunner{

	@Autowired
    private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
//	
	public static void main(String[] args) {
		SpringApplication.run(ExamServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		try {
		User user = new User();
		
		user.setName("Srushti Kumbhalkar");
		user.setUsername("sr1");
		user.setEmail("srushti@gmail.com");
		//user.setPassword("1234");
		user.setPassword(this.bcryptPasswordEncoder.encode("123"));
		user.setPhone("7058069428");
		
		Role role = new Role(0, null);
		role.setRoleId(22);
		role.setRoleName("Admin");
		
		Set<UserRole> userRoleSet =new HashSet<>();
		
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		userRoleSet.add(userRole);
		
		 User user1 = this.userService.createUser(user , userRoleSet);
         System.out.println(user1.getUsername());
		}
         catch(Exception e){
        	 e.printStackTrace();
         }
	}

}
	
	
