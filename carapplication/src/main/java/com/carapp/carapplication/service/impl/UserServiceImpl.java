package com.carapp.carapplication.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.carapp.carapplication.service.UserService;


@Service
public class UserServiceImpl implements UserService
{

	@Override
	public String encodePassword(String password)
	{
	
		
		BCryptPasswordEncoder bce=new BCryptPasswordEncoder();
		String hashedPassword=bce.encode(password);
		
		
		return hashedPassword;
		
		
	}
	
	
	
	
}
