package com.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exam.service.UserDetailsServiceImpl;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private JwtAutheticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private JwtAuthenticationFilter  jwtAuthenticationFilter;
	
	@Bean
    public BCryptPasswordEncoder PasswordEncoder(){
		  return new BCryptPasswordEncoder();
		  }
    

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(PasswordEncoder());
		super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
	
		
	http
      .csrf()
      .disable()
      .cors()
       .disable()
	
		 .authorizeRequests()
       .antMatchers("/generate-token" ,"/user/","/user/test").permitAll()
        .antMatchers(HttpMethod.OPTIONS).permitAll()
       
        .anyRequest().authenticated()
        .and()
       
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        
http.addFilterBefore(jwtAuthenticationFilter , UsernamePasswordAuthenticationFilter.class);


}

	 @Bean
     public AuthenticationManager authenticationManager() throws Exception {
      return super.authenticationManager();
     }

}

	
