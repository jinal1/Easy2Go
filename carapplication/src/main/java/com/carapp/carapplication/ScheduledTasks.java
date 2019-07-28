package com.carapp.carapplication;
import org.springframework.stereotype.Component;
import com.carapp.carapplication.Controller.CarController;
import com.carapp.carapplication.Repository.CarBookingRepository;
import com.carapp.carapplication.Repository.CarRepository;
import com.carapp.carapplication.Repository.CustomerCarBookingRepository;
import com.carapp.carapplication.Repository.CustomerDriverBookingRepository;
import com.carapp.carapplication.Repository.DriverRepository;
import com.carapp.carapplication.Repository.ParkingBookingRepository;
import com.carapp.carapplication.Repository.UserRepository;
import com.carapp.carapplication.model.CustomerCarBooking;
import com.carapp.carapplication.model.Driver;
import com.carapp.carapplication.model.User;
import com.carapp.carapplication.model.car1;
import com.carapp.carapplication.model.carbooking;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;


@Component
public class ScheduledTasks {
	@Autowired
	CustomerCarBookingRepository cCB;
	@Autowired
	UserRepository uR;
	@Autowired
	CarBookingRepository cBR;
	@Autowired
	CarRepository cR;
	@Autowired
	CarController cc;
	@Autowired
	CustomerDriverBookingRepository cDBR;
	@Autowired
	DriverRepository dR;
	@Autowired
	ParkingBookingRepository cPBR;
	
	 
	  
	 //private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	   // private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	  
	    @Scheduled(cron="0 43 23 * * *")
	    public void reportCurrentTime()  throws ParseException
	    {
	     //   log.info("The time is now {}", dateFormat.format(new Date()));
	    	
	    	
	    	
	    	List<car1> cars=new ArrayList<car1>();
	    	String driveremailid="";
	    	 long millis=System.currentTimeMillis();  
	         java.sql.Date date=new java.sql.Date(millis);  
	       System.out.println(date);
	         java.sql.Date tomorrow= new java.sql.Date( date.getTime() + 24*60*60*1000);
	         System.out.println(tomorrow);
	       List<CustomerCarBooking> listofcarbookings= cCB.findAll();
	       for(CustomerCarBooking ccb:listofcarbookings)
	       {
	    	   System.out.println(ccb.getDropoffDate());
	    	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	           java.util.Date date1 = sdf.parse(""+date);
	           java.util.Date date2 = sdf.parse(""+ccb.getDropoffDate());
	    	  if(date1.compareTo(date2)==0)
	    	  {
	    		 int id=ccb.getCustomerid();
	    		 System.out.println("in"+tomorrow);
	    		 
	    		User user=uR.findOne(id);
	    		String email=user.getEmailid();
	    		System.out.println(email);
	    		
	    		 cars=ccb.getCar1();
	    		 System.out.println(email);
	    		for(car1 car:cars){
	    		System.out.println(email+car.getCapacity());
	    		User owner=car.getUser();
	  
	    		System.out.println(owner.getUserName());
	    		String owneremailid=owner.getEmailid();
	    		if(ccb.getDriverRequired().equals("true"))
	    		{
	    			int customercarbookingid=ccb.getBookingId();
	    		Driver d=dR.getDriver(customercarbookingid);
	    			if(d.getEndDate().compareTo(date)==0)
	    			{	
	    				String drivername=d.getDrivername();
	    				User driver=uR.findmailId(drivername);
	    				 driveremailid=driver.getEmailid();
	    				
	    			}
	    			
	    		}
	    		try
	    		{
	    			//cc.notification(driveremailid,"regarding end of your avalibility","today is the last day of your work","jinal2596@gmail.com");
	    			cc.notification(owneremailid, "regarding car rental ending","today is the last date of your car booking for the customer so please check out your car status or keep in touch with admin " , "jinal2596@gmail.com");
	    			cc.notification(email,"regarding return your car to the portal","Today is the last day of your car rental system.please drop your car at dropoff location on time","jinal2596@gmail.com");	
	    		}
	    		catch(Exception e)
	    		{
	    			
	    		}
	    		
	    		
	    	  
	    		
	       }
	       }
	    
	      cc.givereturncardetails(cars);  
}}
	   

	  

}
