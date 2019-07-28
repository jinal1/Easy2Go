package com.carapp.carapplication.service.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.carapp.carapplication.Controller.CarController;
import com.carapp.carapplication.DTO.CarDto;
import com.carapp.carapplication.Repository.CarBookingRepository;
import com.carapp.carapplication.Repository.CarImageRepository;
import com.carapp.carapplication.Repository.CarRepository;
import com.carapp.carapplication.model.CarImages;
import com.carapp.carapplication.model.car1;
import com.carapp.carapplication.model.carbooking;
import com.carapp.carapplication.service.CarService;

@Service
public class CarServiceImpl implements CarService 
{
	@Autowired 
	CarRepository c;
	@Autowired
	CarImageRepository cIR;
	@Autowired
	CarBookingRepository cBR;
	@Autowired
	CarController ab;
	
	public List<java.sql.Date> getcarbookingdate(int car1id)
	{
		carbooking c=cBR.getbookingdata(car1id);
	java.sql.Date p=c.getPickupdate();
	java.sql.Date q=c.getDropoffdate();
	List<java.sql.Date> ab=new ArrayList<java.sql.Date>();
	ab.add(p);
	ab.add(q);
	return ab;
	
	
	
	}
	public 	List<CarDto> getfiltercars(String location,String brand,String model,int year,int price)
	{
		byte[] bytes=new byte[1024];
	String is="true";
		 
		List<car1> listOfCars=c.findfiltercar(location, brand,model,year, price,is);
				List<CarDto> listOfCarDto=new ArrayList<CarDto>();
				//CarDto d=new CarDto();
				
				
				for( car1 cars : listOfCars)
				{
					System.out.println(cars.getisAuthenticated());
					CarDto d=new CarDto();
					int id=cars.getCar1id();
				List<CarImages> images=cIR.findimagepath(id);
				for(CarImages i1:images)
				{
					String path=i1.getImagepath();
				System.out.println(i1.getImagepath());
				
		//int len=images.size();
		//for(int i=0;i<len;i++)
			//{
				String a[]=new String[5];
				a=path.split("/");
				System.out.println(a[1]);
				bytes=ab.getImages(a[1]);
				d.setCar(cars);
				d.setB(bytes);
				d.setImagepath(i1.getImagepath());
				listOfCarDto.add(d);
				}
				//d.setCar(cars);	
			System.out.println("hello id"+cars.getCar1id());
			System.out.println(bytes);
			System.out.println(d);	
					//CarController ab=new CarController();
					//System.out.println(cars.getCar1id());
				}
			return listOfCarDto;
	}
	
	public 	List<CarDto> getsearchcars(carbooking carbooking)
	{
		byte[] bytes=new byte[1024];
	List<carbooking> listofcarbookings=cBR.findAll();
	List<carbooking> listofmatchingcarbooking=new ArrayList<carbooking>();
	List<car1> listOfCars=new ArrayList<car1>();
	java.sql.Date pco=carbooking.getPickupdate();
	System.out.println(pco);

	java.sql.Date dco=carbooking.getDropoffdate();
	System.out.println(dco);
	java.util.Date pco1=new java.util.Date(pco.getDate());
	java.util.Date dco1=new java.util.Date(dco.getDate());
	for( carbooking carb:listofcarbookings)
	{
		
		java.sql.Date p=carb.getPickupdate();
		java.sql.Date q=carb.getDropoffdate();
		
		System.out.println(p+"and"+q);
		java.util.Date p1=new java.util.Date(p.getDate());
		java.util.Date q1=new java.util.Date(q.getDate());
		if((p1.compareTo(pco1)<=0) && (q1.compareTo(dco1)>=0))
		{
		     listofmatchingcarbooking.add(carb);
		     System.out.println(carb.getBookingId());
		}
	
	}
	for(carbooking carb:listofmatchingcarbooking) 
	{
		if(carb.getCar1().getisAuthenticated().equals("true") && carb.getCar1().getisActive().equals("false"))
		{
		 listOfCars.add(carb.getCar1());
		}
	}
		
				List<CarDto> listOfCarDto=new ArrayList<CarDto>();
				//CarDto d=new CarDto();
				
				
				for( car1 cars : listOfCars)
				{
					System.out.println(cars.getisAuthenticated());
					CarDto d=new CarDto();
					int id=cars.getCar1id();
				List<CarImages> images=cIR.findimagepath(id);
				for(CarImages i1:images)
				{
					String path=i1.getImagepath();
				System.out.println(i1.getImagepath());
				
		//int len=images.size();
		//for(int i=0;i<len;i++)
			//{
				String a[]=new String[5];
				a=path.split("/");
				System.out.println(a[1]);
				bytes=ab.getImages(a[1]);
				d.setCar(cars);
				d.setB(bytes);
				d.setImagepath(i1.getImagepath());
				listOfCarDto.add(d);
				}
				//d.setCar(cars);	
			System.out.println("hello id"+cars.getCar1id());
			System.out.println(bytes);
			System.out.println(d);	
					//CarController ab=new CarController();
					//System.out.println(cars.getCar1id());
				}
				
			return listOfCarDto;
	}
	public List<CarDto> findHistoryCar(int id1)
	{
		byte[] bytes=new byte[1024];
		String ast="true";
		 
		List<car1> listOfCars=c.findHistorycar(id1,ast);
				List<CarDto> listOfCarDto=new ArrayList<CarDto>();
				//CarDto d=new CarDto();
				
				
				for( car1 cars : listOfCars)
				{
					System.out.println(cars.getisAuthenticated());
					CarDto d=new CarDto();
					int id=cars.getCar1id();
				List<CarImages> images=cIR.findimagepath(id);
				for(CarImages i1:images)
				{
					String path=i1.getImagepath();
				System.out.println(i1.getImagepath());
				
		//int len=images.size();
		//for(int i=0;i<len;i++)
			//{
				String a[]=new String[5];
				a=path.split("/");
				System.out.println(a[1]);
				bytes=ab.getImages(a[1]);
				d.setCar(cars);
				d.setB(bytes);
				d.setImagepath(i1.getImagepath());
				listOfCarDto.add(d);
				}
				//d.setCar(cars);	
			System.out.println("hello id"+cars.getCar1id());
			System.out.println(bytes);
			System.out.println(d);	
					//CarController ab=new CarController();
					//System.out.println(cars.getCar1id());
				}
				
			return listOfCarDto;
	}
	public void getcarid(carbooking carbooking)
	{
		List<Integer> a;
		java.sql.Date pickupdate=carbooking.getPickupdate();
		java.sql.Date dropoffdate=carbooking.getDropoffdate();
		//Time Pickuptime=carbooking.getPickuptime();
		
		
		
		
		
		//sa=cBR.findcarids(pickupdate,dropoffdate,pickuptime);
		
	}
	
	
	public List<CarDto> findThreeCar(String a1,String b,String c1)
	{
		byte[] bytes=new byte[1024];

		 System.out.print("inside service  findthreecar");
		 String Auth="true";
		 String Active="false";
		 String Portal="true";
		List<car1> listOfCars=c.threeCars(a1,b,c1,Auth,Active,Portal);
		System.out.print("after query");
				List<CarDto> listOfCarDto=new ArrayList<CarDto>();
				//CarDto d=new CarDto();
				
				for( car1 cars : listOfCars)
				{
					CarDto d=new CarDto();
					int id=cars.getCar1id();
				List<CarImages> images=cIR.findimagepath(id);
				for(CarImages i1:images)
				{
					String path=i1.getImagepath();
				System.out.println(i1.getImagepath());
				
		//int len=images.size();
		//for(int i=0;i<len;i++)
			//{
				String a[]=new String[5];
				a=path.split("/");
				System.out.println(a[1]);
				bytes=ab.getImages(a[1]);
				d.setCar(cars);
				d.setB(bytes);
				d.setImagepath(i1.getImagepath());
				listOfCarDto.add(d);
				}
				//d.setCar(cars);	
			System.out.println("hello id"+cars.getCar1id());
			System.out.println(bytes);
			System.out.println(d);	
					//CarController ab=new CarController();
					//System.out.println(cars.getCar1id());
				}
				
			return listOfCarDto;	
	}
	public List<CarDto>  findAllAuthFcar()
	
	{byte[] bytes=new byte[1024];
String bst="false";
 
List<car1> listOfCars=c.findFcar(bst);
		List<CarDto> listOfCarDto=new ArrayList<CarDto>();
		//CarDto d=new CarDto();
		
		for( car1 cars : listOfCars)
		{
			System.out.println(cars.getisAuthenticated());
			CarDto d=new CarDto();
			int id=cars.getCar1id();
		List<CarImages> images=cIR.findimagepath(id);
		for(CarImages i1:images)
		{
			String path=i1.getImagepath();
		System.out.println(i1.getImagepath());
		
//int len=images.size();
//for(int i=0;i<len;i++)
	//{
		String a[]=new String[5];
		a=path.split("/");
		System.out.println(a[1]);
		bytes=ab.getImages(a[1]);
		d.setCar(cars);
		d.setB(bytes);
		d.setImagepath(i1.getImagepath());
		listOfCarDto.add(d);
		}
		//d.setCar(cars);	
	System.out.println("hello id"+cars.getCar1id());
	System.out.println(bytes);
	System.out.println(d);	
			//CarController ab=new CarController();
			//System.out.println(cars.getCar1id());
		}
		
	return listOfCarDto;
	}
	
	
	
	
	
	
	
	
	
public List<CarDto>  findAllAuthcar()
	
	{byte[] bytes=new byte[1024];

 String ast="true";
 String bst="false";
List<car1> listOfCars=c.findAuth(ast,bst);
		List<CarDto> listOfCarDto=new ArrayList<CarDto>();
		//CarDto d=new CarDto();
		
		for( car1 cars : listOfCars)
		{
			CarDto d=new CarDto();
			int id=cars.getCar1id();
		List<CarImages> images=cIR.findimagepath(id);
		for(CarImages i1:images)
		{
			String path=i1.getImagepath();
		System.out.println(i1.getImagepath());
		
//int len=images.size();
//for(int i=0;i<len;i++)
	//{
		String a[]=new String[5];
		a=path.split("/");
		System.out.println(a[1]);
		bytes=ab.getImages(a[1]);
		d.setCar(cars);
		d.setB(bytes);
		d.setImagepath(i1.getImagepath());
		listOfCarDto.add(d);
		
		}
		//d.setCar(cars);	
	System.out.println("hello id"+cars.getCar1id());
	System.out.println(bytes);
	System.out.println(d);	
			//CarController ab=new CarController();
			//System.out.println(cars.getCar1id());
		}
		
	return listOfCarDto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public CarDto findOnecar(int id)
	{
		byte[] bytes=new byte[1024];

		car1 car=c.findOne(id);
		CarDto cd=new CarDto();
		cd.setCar(car);
       
		/*CarImages ci=cIR.find
		 * oneimagepath(id);
		String a=ci.getImagepath();
		String b[];
		b=a.split("//");
		System.out.println(b[2]);*/
		bytes=ab.getImages("i3.png");
	
        
        
		
		cd.setB(bytes);
		System.out.print("in findonecar method");
		return cd;
		
	}
}
