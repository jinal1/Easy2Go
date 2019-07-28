package com.carapp.carapplication.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.carapp.carapplication.DTO.CarDto;
import com.carapp.carapplication.Repository.PlaceHolderRepository;
import com.carapp.carapplication.model.CarImages;
import com.carapp.carapplication.model.PlaceHolder;
import com.carapp.carapplication.model.User;
import java.nio.file.Paths;


@Controller
public class PlaceHolderController 
{


	@Autowired 
	PlaceHolderRepository c;
	
	@RequestMapping(value = "/saveimage2", method = RequestMethod.POST)
	@ResponseBody

	public ResponseEntity<?> imagestore(@RequestParam("file") MultipartFile uploadfile) {
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
		
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	 @RequestMapping(value = "/getfilterparking/{i}", method = RequestMethod.POST)
	  @ResponseBody
	    public ResponseEntity<List<PlaceHolder>> getfilterparking(@PathVariable("i") String i) throws Exception
	  {
		System.out.println("in getfilterparking placeholder java");
	      String values[]=i.split(",");
	      String address=values[0];
			String parkingLocation=values[1];
			String Date=values[2];
			String startTime=values[3];
			String endTime=values[4];
			String yourString=Date+" "+startTime;
			   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			    Date parsedDate = dateFormat.parse(yourString);
			    Timestamp starttime = new java.sql.Timestamp(parsedDate.getTime());
			    System.out.print(starttime);
			    
			    String yourString1=Date+" "+endTime;
				   SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				    Date parsedDate1 = dateFormat1.parse(yourString1);
				    Timestamp endtime = new java.sql.Timestamp(parsedDate1.getTime());
			List<PlaceHolder> parkingdetails=c.getfilterparkingplace(address,parkingLocation,starttime,endtime);
			
	        return new ResponseEntity<List<PlaceHolder>>(parkingdetails, HttpStatus.OK);
	    }
	

	
	 @RequestMapping(value = "/contact/{i}", method = RequestMethod.POST)
	  @ResponseBody
	    public ResponseEntity<?> contactus(@PathVariable("i") String i ) 
	  {
		
	      String values[]=i.split(",");
	      User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      String emailid=user.getEmailid();
	      String messsage=values[2];
	      try
	      {
	      notificationforplaceholder(emailid,"",messsage,"jinal2596@gmail.com");
	      }
	      catch(Exception e) {
	    	  
	      }
	        return new ResponseEntity<>( HttpStatus.OK);
	    }
	

	 @RequestMapping(value = "/findallplaceholders", method = RequestMethod.GET)
	  @ResponseBody
	    public ResponseEntity<List<PlaceHolder>> listAllPlaceHolders() 
	  {
		  System.out.println("helllooooooooooooooooooooooooooo");
		  List<PlaceHolder> listOfPlaceholder=c.findAll();
//		  for(PlaceHolder p:listOfPlaceholder)
//		  {
//			Timestamp ts= p.getStartTime();
//			Date dateTime1=new Date(ts.getDate());
//		    Time dateTime = new Time(ts.getTime());
//		    
//		    System.out.println("DateTime: " + dateTime);
//		    System.out.println("DateTime: " + dateTime1);
//		    
//		    System.out.println(p.getStartTime());
//		  }
//	        if(listOfPlaceholder.isEmpty())
//	        {
//	            return new ResponseEntity<List<PlaceHolder>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//	        }
	        return new ResponseEntity<List<PlaceHolder>>(listOfPlaceholder, HttpStatus.OK);
	    }
	
	
	@RequestMapping(value = "/savePlaceholder", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<PlaceHolder>  initial1(@RequestBody PlaceHolder placeholder)
	{
		
	
		
		
		User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		placeholder.setUser(user);
	    c.save( placeholder);
		
		 String to=user.getEmailid();
		 try
		 {
	    notificationforplaceholder(to,"Regarding Regestration ","your parking place is succassfully added to easy2go portal","jinal2596@gmail.com");
		 }
		 catch(Exception e)
		 {
			 
		 }
			
			
		
	return new ResponseEntity<PlaceHolder>(placeholder,HttpStatus.OK);
	
	}
	
	
	@RequestMapping(value = "/donotificationforplaceholder")
	@ResponseBody
	public ResponseEntity<?> notificationforplaceholder(String to,String sub,String msg,String user) throws Exception {

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
