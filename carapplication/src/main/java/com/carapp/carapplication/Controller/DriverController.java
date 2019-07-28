package com.carapp.carapplication.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.carapp.carapplication.Repository.DriverRepository;

import com.carapp.carapplication.model.Driver;
import com.carapp.carapplication.model.User;

import java.nio.file.Paths;

import java.text.SimpleDateFormat;
@Controller
public class DriverController
{

	@Autowired 
DriverRepository dR;
	@RequestMapping(value = "/durationdriver/{id}", method = RequestMethod.POST)
	  @ResponseBody
	    public ResponseEntity<List<Driver>> listDurationAllDrivers(@PathVariable("id") String id) throws Exception
	  {
	
		 String a[]=id.split(",");
		 
		  System.out.println("helllooooooooooooooooooooooooooo");
		
		  String startDate=a[0];
		  System.out.println(a[0]+a[1]);
		  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		  java.util.Date date = sdf1.parse(startDate);
		  java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
		  
		  String endDate=a[1];
		  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		  java.util.Date date1 = sdf2.parse(endDate);
		  java.sql.Date sqlEndDate = new java.sql.Date(date1.getTime()); 
		  System.out.println("hello"+sqlStartDate+sqlEndDate);
		  List<Driver> listOfDrivers=dR.findAll();
		  List<Driver> durationDriver=new ArrayList<Driver>();
		  for(Driver d:listOfDrivers)
		  {
			  if(d.getStartDate().compareTo(sqlStartDate)==0 && d.getEndDate().compareTo(sqlEndDate)==0)
			  {
				  System.out.print(d.getStartDate());
				  durationDriver.add(d);
			  }
		  }
		
	        if(listOfDrivers.isEmpty())
	        {
	        	   return new ResponseEntity<List<Driver>>(durationDriver,HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Driver>>(durationDriver, HttpStatus.OK);
		
		
	    }
	
	
	 @RequestMapping(value = "/driver", method = RequestMethod.GET)
	  @ResponseBody
	    public ResponseEntity<List<Driver>> listAllDrivers() 
	  {
		  System.out.println("helllooooooooooooooooooooooooooo");
		  List<Driver> listOfDrivers=dR.findAll();
	        if(listOfDrivers.isEmpty())
	        {
	            return new ResponseEntity<List<Driver>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Driver>>(listOfDrivers, HttpStatus.OK);
	    }
	

	
	@RequestMapping(value = "/saveDriver", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Driver>  savemethod(@RequestBody Driver driver)
	{
		
		
//	String a=driver.getLicense();
	//String filepath="E:/"+"/images/"+"/"+a;
			System.out.println("hello"+driver.getStartDate());
			System.out.print(driver.getEndDate());
	//driver.setLicense(filepath);
	    dR.save(driver);
		User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		 String to=user.getEmailid();
	    try
		 {
	    notificationfordriver(to,"Regarding Regestration ","you are succassfully added as a driver in easy2go portal","jinal2596@gmail.com");
		 }
		 catch(Exception e)
		 {
			 
		 }
			
			
		
	return new ResponseEntity<Driver>(driver,HttpStatus.OK);
	
	}
	@RequestMapping(value = "/saveimage1", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> imagestore1(@RequestParam("file") MultipartFile uploadfile) {
		try {
			// Get the filename and build the local file path
			String filename = uploadfile.getOriginalFilename();
			System.out.print("hellloooooooooo" + filename);
			// String directory = env.getProperty("com.carapp.carapplication.images");
			String directory ="E://easy2go//src//main//resources//static//pics";
			// UserController uc=new UserController();
			// int uid=uc.getUser();
			System.out.print("hellloooooooooo");
			String filepath = Paths.get(directory, filename).toString();
			//String filepath="E:/"+"/easy2go/"+"/src/"+"/main/"+"/resouces/"+"/static/"+"/pics/"+"/"+filename;
			System.out.println(filepath);
			

			/*
			 * String filepath="C:\\Users\\JINAL\\Desktop\\i3.png"; String
			 * filepath1="E:\\images\\i4.png"; FileInputStream fis=new
			 * FileInputStream(filepath); FileOutputStream fos=new
			 * FileOutputStream(filepath1); int content; while ((content = fis.read()) !=
			 * -1) {
			 * 
			 * fos.write(content); } fis.close(); fos.close();
			 */

			// Save the file locally
			System.out.print("hellloooooooooo");
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();
		//	User u=car.getUser();
			//String to= u.getEmailid();
			String to="jinal.140410107100@gmail.com";
			String sub="car verification";
			String msg="your car has been successfully sent to admin for verification";
			String from="jinal2596@gmail.com";
			
		//	notification(to,sub,msg,from);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping(value = "/donotificationfordriver")
	@ResponseBody
	public ResponseEntity<?> notificationfordriver(String to,String sub,String msg,String user) throws Exception {

		try {

			String password = "brxrehtxyvmsluru";
			//String to = "jinal.140410107100@gmail.com";
			//String sub = "ONE TIME PASSWORD";
			//String msg = "Your varification code is : ";
			String host = "smtp.gmail.com";
			//final String user = "jinal2596@gmail.com";// change accordingly

			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.host", host);

			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});
			  
				     MimeMessage message = new MimeMessage(session);  
				     message.setFrom(new InternetAddress(user));  
				     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
				     message.setSubject(sub);  
				     message.setText(msg);  
				       
				    //send the message  
				     Transport.send(message);  
			Transport.send(message);
			System.out.println("mail send successfully....");
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		/*
		 * 
		 * JavaMailSender emailSender = new JavaMailSenderImpl();
		 * 
		 * SimpleMailMessage mail=new SimpleMailMessage();
		 * mail.setTo("jinal2596@gmail.com");
		 * mail.setFrom("jinal.140410107100@gmail.com"); mail.setSubject("hello");
		 * mail.setText("hi");
		 * 
		 * emailSender.send(mail);
		 */

		return new ResponseEntity<>(HttpStatus.OK);
	}


}


	

