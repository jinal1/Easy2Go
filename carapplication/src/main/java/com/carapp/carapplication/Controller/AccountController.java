package com.carapp.carapplication.Controller;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carapp.carapplication.Repository.AccountRepository;
import com.carapp.carapplication.Repository.AccountdetailRepository;
import com.carapp.carapplication.Repository.CarBookingRepository;
import com.carapp.carapplication.Repository.CarRepository;
import com.carapp.carapplication.Repository.CustomerCarBookingRepository;
import com.carapp.carapplication.Repository.CustomerDriverBookingRepository;
import com.carapp.carapplication.Repository.DriverRepository;
import com.carapp.carapplication.Repository.ParkingBookingRepository;
import com.carapp.carapplication.Repository.PlaceHolderRepository;
import com.carapp.carapplication.Repository.UserRepository;
import com.carapp.carapplication.model.Account;
import com.carapp.carapplication.model.AccountDetails;
import com.carapp.carapplication.model.Bank;
import com.carapp.carapplication.model.User;
import com.carapp.carapplication.model.car1;
import com.carapp.carapplication.model.CustomerCarBooking;
import com.carapp.carapplication.model.CustomerDriverBooking;
import com.carapp.carapplication.model.CustomerParkingBooking;
import com.carapp.carapplication.model.Driver;
import com.carapp.carapplication.model.PlaceHolder;
@Controller
public class AccountController {
	int random=0;
	@Autowired
	AccountdetailRepository aDR;
	@Autowired
	AccountRepository aR;
	@Autowired
	CarRepository c;
	@Autowired
	CustomerCarBookingRepository cCB;
	@Autowired
	DriverRepository dR;
	@Autowired
	CustomerDriverBookingRepository cDBR;
	@Autowired
	UserRepository uR;
	@Autowired
	PlaceHolderRepository pHR;
	@Autowired
    ParkingBookingRepository cPB;
	@RequestMapping(value ="/saveparkingdetail/{id}", method = RequestMethod.POST)
	public  ResponseEntity<Map<String,Integer>> saveparkingdetail(@PathVariable("id") String id) 
	{
		Map<String,Integer> map = new  HashMap<>();
		
		String values[]=id.split(",");
		int price=Integer.parseInt(values[0]);
		System.out.println("hello jinal"+price);
		int placeholderid=Integer.parseInt(values[1]);
		PlaceHolder ph=pHR.findOne(placeholderid);
		Timestamp starttime=ph.getStartTime();
		Timestamp endtime=ph.getEndTime();
		
		int date=starttime.getDate();
		int month=starttime.getMonth();
		int year=starttime.getYear();
		
		System.out.println("hello jinal"+date+month+year);
		int starthour=starttime.getHours();
		int startmin=starttime.getMinutes();
		int endhour=endtime.getHours();
		int endmin=endtime.getMinutes();
		int starthourfrommin=startmin/60;
		int totalstarthour=starthour+starthourfrommin;
		int endhourfrommin=endmin/60;
		int totalendhour=endhour+endhourfrommin;
		int difofhour=totalendhour-totalstarthour;
		System.out.println("hello jinal"+difofhour);
		
		
		int  totalprice=difofhour*price;
		String Address=ph.getAddress();
		String parkingLocation=ph.getParkingLocation();
		String dbaddress=parkingLocation+","+Address;
		String dbstarttime=starthour+":"+startmin;
		String dbendtime=endhour+":"+endmin;
		Date dbstartDate=new Date(year,month,date);
		 User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 int userid=user.getUserid();
		 System.out.println("hello jinal"+userid);
		CustomerCarBooking customercarbooking= cCB.findBooking(userid);	 
		
		int carbookingid=customercarbooking.getBookingId();
		System.out.println("in account controller"+carbookingid);
		car1 car=c.findcarid(carbookingid);
		int carid= car.getCar1id();
	CustomerParkingBooking cpbooking=new CustomerParkingBooking();
	
	
	cpbooking.setCar1(car);
	System.out.println("jinal"+carbookingid);
	cpbooking.setCustomerid(userid);
	System.out.println("payal"+carbookingid);
	cpbooking.setDropoffDate(dbstartDate);
	cpbooking.setPickupDate(dbstartDate);
	cpbooking.setDropoffTime(dbendtime);
	cpbooking.setPickupTime(dbstarttime);
	cpbooking.setTotalprice(totalprice);
	cpbooking.setPlaceholder(ph);
	cpbooking.setLocation(dbaddress);
	System.out.println("hello jinal"+carbookingid);
	cPB.save(cpbooking);

	System.out.println("hello payal"+carbookingid);
	
	PlaceHolder ph1=pHR.findOne(placeholderid);
	System.out.println("ohoooooooooooooo"+carbookingid);
	ph1.setCustomerparkingbooking(cpbooking);
	
	pHR.save(ph1);
	
		System.out.println(price);
		int customerid=userid;
		Account account=aR.findCustomerAccount(customerid);
		
	
		int availbalance=account.getBalance();
		System.out.println(availbalance);
		if(totalprice<=availbalance)
		{
			Random randomno = new Random();
			 random=randomno.nextInt(10000);
			 System.out.println(random);
		
			 try
			 {
				 System.out.println("in mail");
				 notificationforaccount(user.getEmailid(),"regarding payment"," your security code for parking payment is "+random,"jinal2596@gmail.com");
				 System.out.println("in mail 2");
					
			 }
			 catch(Exception e)
			 {
				 
			 }
			 
			 
			 
			 
			
		}
		map.put("placeholderid",placeholderid);
		map.put("totalprice",totalprice);
		
		return new ResponseEntity<Map<String,Integer>>(map,HttpStatus.OK);
		
	
	}
@RequestMapping(value ="/makepayment2/{id}", method = RequestMethod.POST)
@ResponseBody
	public ResponseEntity<Map<String,String>>  makepayment2(@PathVariable("id") String id) 
	{
	Map<String,String> map = new  HashMap<>();
	String values[]=id.split(",");
	int placeholderid=Integer.parseInt(values[0]);
	
	int totalprice=Integer.parseInt(values[1]);
	
	CustomerParkingBooking cusrparbook=cPB.findbyplaceholderid(placeholderid);
	PlaceHolder plhol=pHR.findbypid(placeholderid);
	plhol.setIsActive("true");
	pHR.save(plhol);
	
	String date=""+cusrparbook.getPickupDate();
	String location=""+cusrparbook.getLocation();
	String starttime=""+cusrparbook.getPickupTime();
	String endtime=""+cusrparbook.getDropoffTime();
	String toprice=""+cusrparbook.getTotalprice();
	car1 car=cusrparbook.getCar1();
	String carname=car.getCompanyname();
	
	map.put("date", date);
	map.put("starttime", starttime);
	map.put("endtime", endtime);
	map.put("totalprice", toprice);
	map.put("location",location);
	map.put("carname",carname);
	
	
	
	return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
	
	}
@RequestMapping(value ="/makepayment1/{id}", method = RequestMethod.POST)
	
	public  ResponseEntity<Map<String,String>> makepayment1(@PathVariable("id") String id) 
	{
		Map<String,String> map = new  HashMap<>();
		
		String name="";
		String cname="";
		String pdate="";
		String ddate="";
		String drivername="";
		String values[]=id.split(",");
		int price=Integer.parseInt(values[0]);
		String Totalprice=""+price;
		int carid=Integer.parseInt(values[1]);
		int driverid=Integer.parseInt(values[2]);
		int sc=Integer.parseInt(values[3]);
		int pc=Integer.parseInt(values[4]);
		int pd=Integer.parseInt(values[5]);
		User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(sc==random)
		{
			Driver d=dR.findOne(driverid);
			d.setIsActive("true");
			dR.save(d);
			drivername=d.getDrivername();
			User u=uR.findmailId(drivername);
			String driveremailid=u.getEmailid();
			name=user.getUserName();
			int userid=user.getUserid();
			System.out.println("in random");
			CustomerCarBooking cBooking=cCB.findBooking(userid);
			
			pdate=""+cBooking.getPickupDate();
			ddate=""+cBooking.getDropoffDate();
			cBooking.setTotalprice(pc);
			cCB.save(cBooking);
			car1 car=c.findOne(carid);
			cname=car.getCompanyname();
			car.setisActive("true");
			c.save(car);
			User owner=car.getUser();
			String owneremailid=owner.getEmailid();
			String customeremailid=user.getEmailid();
			int ownerid=owner.getUserid();
			System.out.println("ownerid"+ownerid);
			
			int customerid=userid;
			System.out.println("customerid"+customerid);
			Account customeraccount=aR.findCustomerAccount(customerid);
			
			int balance=customeraccount.getBalance();
			int currentupdatedbalace=balance-price;
			customeraccount.setBalance(currentupdatedbalace);
		
			aR.save(customeraccount);
			BigInteger accountno=customeraccount.getAccountno();
			AccountDetails accountdetail=aDR.findBank(accountno);
			accountdetail.setBalance(currentupdatedbalace);
			aDR.save(accountdetail);
			Account adminaccount=aR.findOne(4);
			int adminbalance=adminaccount.getBalance();
			int adminupdatebalance=adminbalance+price;
			
			
			
			
			adminaccount.setBalance(adminupdatebalance);
			aR.save(adminaccount);
			BigInteger adminaccountno=adminaccount.getAccountno();
			AccountDetails adminaccountdetail=aDR.findBank(accountno);
			adminaccountdetail.setBalance(adminupdatebalance);
			aDR.save(adminaccountdetail);
		
			
			
			try
			{
			notificationforaccount(owneremailid,"regarding booking","you has been successfully booked as driver and your current balance is","jinal2596@gmail.com");
			notificationforaccount(owneremailid,"regarding booking","your car has been successfully booked and your current balance is","jinal2596@gmail.com");
			notificationforaccount(customeremailid,"regarding booking","your payment has been successfully done and now your current balance is"+currentupdatedbalace,"jinal2596@gmail.com");
			}
			catch(Exception e)
			{
				
			}
			
		}
		String pc1=""+pc;
		String pd1=""+pd;
		map.put("name",name);
		map.put("priceofcar",pc1);
		map.put("cname", cname);
		map.put("pdate", pdate);
		map.put("ddate",ddate);
		map.put("drivername", drivername);
		map.put("priceofdriver",pd1 );
		map.put("totalprice", Totalprice);
		
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
}
@RequestMapping(value ="/makepayment/{id}", method = RequestMethod.POST)
	
	public  ResponseEntity<Map<String,String>> makepayment(@PathVariable("id") String id) 
	{
		Map<String,String> map = new  HashMap<>();
		
		String name="";
		String cname="";
		String pdate="";
		String ddate="";
		
		String values[]=id.split(",");
		int price=Integer.parseInt(values[0]);
		String Totalprice=""+price;
		int carid=Integer.parseInt(values[1]);
		int sc=Integer.parseInt(values[2]);
		User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(sc==random)
		{
			name=user.getUserName();
			int userid=user.getUserid();
			System.out.println("in random");
			CustomerCarBooking cBooking=cCB.findBooking(userid);
			
			pdate=""+cBooking.getPickupDate();
			ddate=""+cBooking.getDropoffDate();
			cBooking.setTotalprice(price);
			cCB.save(cBooking);
			car1 car=c.findOne(carid);
			cname=car.getCompanyname();
			car.setisActive("true");
			c.save(car);
			User owner=car.getUser();
			String owneremailid=owner.getEmailid();
			String customeremailid=user.getEmailid();
			int ownerid=owner.getUserid();
			System.out.println("ownerid"+ownerid);
			
			int customerid=userid;
			System.out.println("customerid"+customerid);
			Account customeraccount=aR.findCustomerAccount(customerid);
			Account owneraccount=aR.findCustomerAccount(ownerid);
			
			int balance=customeraccount.getBalance();
			int currentupdatedbalace=balance-price;
			customeraccount.setBalance(currentupdatedbalace);
		
			aR.save(customeraccount);
			BigInteger accountno=customeraccount.getAccountno();
			AccountDetails accountdetail=aDR.findBank(accountno);
			accountdetail.setBalance(currentupdatedbalace);
			aDR.save(accountdetail);
			Account adminaccount=aR.findOne(4);
			int adminbalance=adminaccount.getBalance();
			//int adminprice=(price*10)/100;
			int adminupdatebalance=adminbalance+price;

			adminaccount.setBalance(adminupdatebalance);
			aR.save(adminaccount);
			BigInteger adminaccountno=adminaccount.getAccountno();
			AccountDetails adminaccountdetail=aDR.findBank(accountno);
			adminaccountdetail.setBalance(adminupdatebalance);
			aDR.save(adminaccountdetail);
			/*int obalance=owneraccount.getBalance();
			int ownerprice=price-adminprice;
			int updateobalance=obalance+ownerprice;
			owneraccount.setBalance(updateobalance);
			aR.save(owneraccount);
			BigInteger accountno1=owneraccount.getAccountno();
			AccountDetails oaccountdetail=aDR.findBank(accountno1);
			oaccountdetail.setBalance(updateobalance);
			aDR.save(oaccountdetail);*/
			
	
			try
			{
			notificationforaccount(owneremailid,"regarding booking","your car has been successfully booked","jinal2596@gmail.com");
			notificationforaccount(customeremailid,"regarding booking","your payment has been successfully done and now your current balance is"+currentupdatedbalace,"jinal2596@gmail.com");
			}
			catch(Exception e)
			{
				
			}
			
		}
		map.put("name",name);
		map.put("totalprice",Totalprice);
		map.put("cname", cname);
		map.put("pdate", pdate);
		map.put("ddate",ddate);
		
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
}

	@RequestMapping(value ="/saveaccountdetails/{id}", method = RequestMethod.POST)
	
	public  ResponseEntity<Map<String,Integer>> saveaccountdetails(@RequestBody Account account,@PathVariable("id") String id) 
	{
		Map<String,Integer> map = new  HashMap<>();
		
		BigInteger accountno=account.getAccountno();
		String values[]=id.split(",");
		String bankname=values[0];
		System.out.println(bankname);
		System.out.println(accountno);
		int price=Integer.parseInt(values[1]);
		System.out.println(price);
		int idofcar=Integer.parseInt(values[2]);
	AccountDetails listofaccountdetails=aDR.findBank(accountno);
	Bank b1=listofaccountdetails.getBankObj();
	String realbankname=b1.getBankname();
	System.out.println(realbankname);
	
	if(realbankname.equals(bankname))
	{
		int availbalance=listofaccountdetails.getBalance();
		System.out.println(availbalance);
		if(price<=availbalance)
		{
			Random randomno = new Random();
			 random=randomno.nextInt(10000);
			 System.out.println(random);
			account.setBalance(availbalance);
			account.setBankObj(b1);
			 User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 account.setUser(user);
				aR.save(account);
			 try
			 {
				 System.out.println("in mail");
				 notificationforaccount(user.getEmailid(),"regarding payment"," your security code is "+random,"jinal2596@gmail.com");
				 System.out.println("in mail 2");
					
			 }
			 catch(Exception e)
			 {
				 
			 }
			
		}
	}
	
	map.put("random",random);
	map.put("price",price);
	map.put("idofcar",idofcar);
	
		return new ResponseEntity<Map<String,Integer>>(map,HttpStatus.OK);
}
	
	
	
	
	
@RequestMapping(value ="/saveaccountdetailsopd/{id}", method = RequestMethod.POST)
public  ResponseEntity<Account> saveaccountdetailsopd(@RequestBody Account account,@PathVariable("id") String id) 
{
	BigInteger accountno=account.getAccountno();
	String bankname=id;
	AccountDetails listofaccountdetails=aDR.findBank(accountno);
	Bank b1=listofaccountdetails.getBankObj();
	String realbankname=b1.getBankname();
	System.out.println(realbankname);
	
	if(realbankname.equals(bankname))
	{
		int availbalance=listofaccountdetails.getBalance();
		System.out.println(availbalance);
		account.setBalance(availbalance);
		account.setAccountno(accountno);
		
		account.setBankObj(b1);
	    User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    account.setUser(user);
		aR.save(account);
			
	}
	
	
	return new ResponseEntity<Account>(HttpStatus.OK);
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value ="/saveaccountdetails1/{id}", method = RequestMethod.POST)
	
	public  ResponseEntity<Map<String,Integer>> saveaccountdetails1(@RequestBody Account account,@PathVariable("id") String id) 
	{
		Map<String,Integer> map = new  HashMap<>();
		
		BigInteger accountno=account.getAccountno();
		String values[]=id.split(",");
		String bankname=values[0];
		System.out.println(bankname);
		System.out.println(accountno);
		int price=Integer.parseInt(values[1]);
		System.out.println(price);
		int idofcar=Integer.parseInt(values[2]);
		
		int driverid=Integer.parseInt(values[3]);
		int priceofcar=Integer.parseInt(values[4]);
		int priceofdriver=Integer.parseInt(values[5]);
	AccountDetails listofaccountdetails=aDR.findBank(accountno);
	Bank b1=listofaccountdetails.getBankObj();
	String realbankname=b1.getBankname();
	System.out.println(realbankname);
	
	if(realbankname.equals(bankname))
	{
		int availbalance=listofaccountdetails.getBalance();
		System.out.println(availbalance);
		if(price<=availbalance)
		{
			Random randomno = new Random();
			 random=randomno.nextInt(10000);
			 System.out.println(random);
			account.setBalance(availbalance);
			account.setBankObj(b1);
			 User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 account.setUser(user);
				aR.save(account);
			 try
			 {
				 System.out.println("in mail");
				 notificationforaccount(user.getEmailid(),"regarding payment"," your security code is "+random,"jinal2596@gmail.com");
				 System.out.println("in mail 2");
					
			 }
			 catch(Exception e)
			 {
				 
			 }
			
		}
	}
	
	map.put("random",random);
	map.put("price",price);
	map.put("idofcar",idofcar);
	map.put("driverid",driverid);
	map.put("priceofcar",priceofcar );
	map.put("priceofdriver",priceofdriver );
	
		return new ResponseEntity<Map<String,Integer>>(map,HttpStatus.OK);
}

	
	@RequestMapping(value = "/donotificationforaccount")
	@ResponseBody
	public ResponseEntity<?> notificationforaccount(String to,String sub,String msg,String user) throws Exception
	{

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


	
	
@RequestMapping(value ="/calculatedriverprice/{id}", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<Map<String,String>> calculatedriverprice(@PathVariable("id") String id)throws Exception
	{
	Map<String,String> map = new  HashMap<>();
	Map<Integer,Integer> map1 = new  HashMap<>();
	String values[]=id.split(",");
	int carid=Integer.parseInt(values[0]);
	

		String startDate=values[1];
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date3 = sdf1.parse(startDate);
		java.sql.Date sqlStartDate = new java.sql.Date(date3.getTime());
		String enddate=values[2];
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date4 = sdf2.parse(enddate);
		java.sql.Date sqlEndDate = new java.sql.Date(date4.getTime());
	

	int totalpriceofcar=Integer.parseInt(values[3]);
	int driverid=Integer.parseInt(values[4]);
	
	
	
	int year=sqlStartDate.getYear()+1900;
	System.out.println("8");
	int year1=sqlEndDate.getYear()+1900;
	System.out.println("9"+year+year1);
	map1.put(1,31);
	map1.put(2, 28);
	map1.put(3,31);
	map1.put(4, 30);
	map1.put(5, 31);
	map1.put(6, 30);
	map1.put(7, 31);
	map1.put(8, 31);
	map1.put(9,30);
	map1.put(10, 31);
	map1.put(11,30);
	map1.put(12, 31);
	System.out.println("10");
	int totaldays=0;
	int mon[]= new int[12];
	int yeararray[]= new int[15];
	int i=0;
	int yearl=year+1;
	//for different year
	if(year!=year1)
	{
		int ydif=year1-year;
		int month=sqlStartDate.getMonth()+1;
		int month1=sqlEndDate.getMonth()+1;
		int monthl=month;
		int monthl1=month1;
		if(ydif==1)
		{
			int daysinstartdate=map1.get(month);
			int daysinenddate=map1.get(month1);
			int date=sqlStartDate.getDate();
			
			int date1=sqlEndDate.getDate();
			System.out.println(daysinstartdate);
			System.out.println(daysinenddate);
			System.out.println(date);
			System.out.println(date1);
			totaldays=totaldays+(daysinstartdate-date+1);
			System.out.println(totaldays);
			while(monthl<=12)
			{
				
			totaldays=totaldays+(map1.get(monthl));
			monthl=monthl+1;
			System.out.println(totaldays);
			}
			totaldays=totaldays+date1;
			System.out.println(totaldays);
		for(int j=1;j<month1;j++)
		{
			totaldays=totaldays+map1.get(j);
			System.out.println(totaldays);
		}
		}
		else
		{
			System.out.println(""+ydif);
			while(yearl<year1)
			{
				
			yeararray[i]=yearl;
			i++;
			yearl=yearl+1;
			
			
			if(yeararray[i]%4==0)
			{
				totaldays=totaldays+366;
			}
			else
			{
				totaldays=totaldays+365;
			}
			
			System.out.println(totaldays);
			}
			int daysinstartdate=map1.get(month);
			int daysinenddate=map1.get(month1);
			int date=sqlStartDate.getDate();
			
			int date1=sqlEndDate.getDate();
			System.out.println(daysinstartdate);
			System.out.println(daysinenddate);
			System.out.println(date);
			System.out.println(date1);
			totaldays=totaldays+(daysinstartdate-date+1);
			System.out.println(totaldays);
			while(monthl<=12)
			{
				
			totaldays=totaldays+(map1.get(monthl));
			monthl=monthl+1;
			System.out.println(totaldays);
			}
			totaldays=totaldays+date1;
			System.out.println(totaldays);
		for(int j=1;j<month1;j++)
		{
			totaldays=totaldays+map1.get(j);
			System.out.println(totaldays);
		}
		System.out.println("in year"+totaldays);
		}
		
		
	}
	
	//for same year
	if(year==year1)
	{
		totaldays=0;
		i=0;
		int month=sqlStartDate.getMonth()+1;
		int month1=sqlEndDate.getMonth()+1;
		int monthl=month+1;
		if(month<=month1)
		{
			if(year%4==0)
			{
				map1.put(2,29);
			}
			int daysinstartdate=map1.get(month);
		int daysinenddate=map1.get(month1);
		
		int date=sqlStartDate.getDate();
		int date1=sqlEndDate.getDate();
			int dif=month1-month;
			System.out.println(dif);
			System.out.println(month);
			System.out.println(month1);
			System.out.println(monthl);
			if(dif==1)
			{
				
				System.out.println(totaldays);
				System.out.println(daysinstartdate);
				System.out.println(date);
				System.out.println(daysinenddate);
				totaldays=totaldays+(daysinstartdate-date+1)+date1;
				
				System.out.println("in month"+totaldays);
			}
			else
			{
			while(monthl<month1)
			{
			mon[i]=monthl;
			System.out.println(mon[i]);
			System.out.print(monthl);
			totaldays=totaldays+map1.get(mon[i]);
			
			System.out.println(totaldays);
			monthl=monthl+1;
			i=i+1;
			}
		
			
			
			if(date<=daysinstartdate && date1<=daysinenddate)
			{
				totaldays=totaldays+(daysinstartdate-date+1)+date1;
				System.out.println(totaldays);
				System.out.println(daysinstartdate);
				System.out.println(date1);
			}
			System.out.println("different month"+totaldays);
			}
			
		}
			
	}
	int price=Integer.parseInt(values[5]);
	price=price*totaldays;
	String price2=""+price;
	
	User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	int userid=user.getUserid();
	CustomerCarBooking ccbooking=cCB.findBooking(userid);
	Driver d=dR.findOne(driverid);
	d.setCustomercarbooking(ccbooking);
	int totalprice1=price+totalpriceofcar;
	String totalprice=""+totalprice1;
	CustomerDriverBooking cdb=new CustomerDriverBooking();
	cdb.setCustomerid(userid);
	cdb.setTotalprice(price);
	cDBR.save(cdb);
	String did=""+driverid;
	String carid1=""+carid;
	String totalpriceofcar1=totalpriceofcar+"";
	map.put("car1id",carid1);
	map.put("priceofcar",totalpriceofcar1);
	String priceofdriver=""+price;
	map.put("priceofdriver", priceofdriver);
	map.put("driverid",did);
	map.put("totalprice", totalprice);
	
	return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
	
	}
	
}