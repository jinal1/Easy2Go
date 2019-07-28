package com.carapp.carapplication.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.carapp.carapplication.Repository.UserRepository;
import com.carapp.carapplication.Repository.UserRoleRepository;
import com.carapp.carapplication.model.User;



@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	private final UserRepository userRepository;
	private final UserRoleRepository userRolesRepository;
	
	@Autowired
    public CustomUserDetailsService(UserRepository userRepository,UserRoleRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository=userRolesRepository;
    }
	
        
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUserName(username);
		if(null == user)
		{
			throw new UsernameNotFoundException("No user present with username: "+username);
		}
		else
		{
			List<String> userRoles=userRolesRepository.findRoleByUserName(username);
			System.out.println(username);
			return new CustomUserDetails(user,userRoles);
		}
	}
		
}
