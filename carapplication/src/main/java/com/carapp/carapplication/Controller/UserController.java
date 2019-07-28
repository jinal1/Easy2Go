package com.carapp.carapplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.carapp.carapplication.Repository.UserRepository;
import com.carapp.carapplication.Repository.UserRoleRepository;

import com.carapp.carapplication.model.User;
import com.carapp.carapplication.model.UserRole;
import com.carapp.carapplication.service.UserService;


//import security.CustomUserDetails;

@Controller
public class UserController
{
	@Autowired 
	UserRepository c;
	@Autowired
	UserRoleRepository uRR;
	
	@Autowired
	UserService p;
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> update(@RequestBody User user1)
	{
		System.out.println("in update user");
		//String name=user1.getUserName();
		int id=user1.getUserid();
		User user=c.findOne(id);
		user.setCity(user.getCity());
		user.setDob(user.getDob());
		user.setMobilenumber(user.getMobilenumber());
		user.setEmailid(user.getEmailid());
		user.setAddress(user.getAddress());
		c.save(user);
	return new ResponseEntity<User>(user1,HttpStatus.OK);
	
	}
	
	
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User>  initial1(@RequestBody User user1)
	{
		try
		{
		String password=user1.getPassword();
		String bcryptedpassword=p.encodePassword(password);
		user1.setPassword(bcryptedpassword);
		int a1=1;
		user1.setEnabled(a1);
	    c.save(user1);
	    UserRole a=new UserRole();
	    String roleof="ROLE_USER";
	   // System.out.println("hiiiiii"+role);
	    a.setRole(roleof);
	    a.setUserid(user1.getUserid());
	    uRR.save(a);
	    
		}
		catch(Exception e)
		{
			
			
			
		}
	return new ResponseEntity<User>(user1,HttpStatus.OK);
	
	}
	
	
	
	 @RequestMapping(value = "/user", method = RequestMethod.GET)
	  @ResponseBody
	    public ResponseEntity<List<User>> listAllUsers() 
	  {
		  System.out.println("helllooooooooooooooooooooooooooo");
		  List<User> listOfUser=c.findAll();
		  
	        if(listOfUser.isEmpty())
	        {
	            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<User>>(listOfUser, HttpStatus.OK);
	    }
	

	  
	  @RequestMapping(value="/getUserName" ,method = RequestMethod.GET)
	  @ResponseBody
		public User getUser()
		{
			System.out.print("inside get user method");
		  
	        
	    	System.out.print("inside get user method");
	    	
			int a;
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		      String name = auth.getName(); //get logged in username

System.out.println(name);
			
		   System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			 User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 System.out.println("heelooo"+user);
		
		
		/*	System.out.print("inside get user method");
		String nameofuser=user.getUserName();
			a=user.getUserid();*/
//return a;
			return user;
			
			
		}
}
