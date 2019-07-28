package com.carapp.carapplication.model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

//import javax.persistence.PrimaryKeyJoinColumn;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
@Entity
public class car1
{   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int car1id;
	private String isAuthenticated;
	private String isActive;
	private String inPortal;
	
	public String getInPortal() {
		return inPortal;
	}
	public void setInPortal(String inPortal) {
		this.inPortal = inPortal;
	}
	public String getisActive() 
	{
		return isActive;
	}
	public void setisActive(String isActive) {
		this.isActive = isActive;
	}
	public List<CarImages> getCarImages() {
		return CarImages;
	}
	public void setCarImages(List<CarImages> carImages) {
		CarImages = carImages;
	}

	private String cityname;
	private int year;
	private String companyname;
	private String model;
	private String transmission;
	private String kmdriven;
	private String vehicletype;
	private String marketvalue;
	private String color;
	private int capacity;
	private String fueltype;
	private String carplatenumber;
	// private String[] features;
	private String cardescription;
	private int price;
	
	private String comanyinfo;
	private String businessaddress;
	private String website;
	private String insuranceprovider;
	private String policynumber;
	
	@JsonIgnore
	 @ManyToOne
	    @JoinColumn(name = "bookingId")
	  private CustomerCarBooking custcarbook;


	public String getisAuthenticated() {
		return isAuthenticated;
	}
	public void setisAuthenticated(String isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	
	public CustomerCarBooking getCustcarbook() {
		return custcarbook;
	}
	public void setCustcarbook(CustomerCarBooking custcarbook) {
		this.custcarbook = custcarbook;
	}

	@JsonIgnore
	 @ManyToOne
	    @JoinColumn(name = "userid")
	  private User user;
	  
	@OneToOne
	@JoinColumn(name = "carfeatureid")
	private carfeature carfeature;
	@JsonIgnore
	
	@OneToMany(mappedBy = "car1",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CarImages> CarImages;
	 public car1()
	 {
	 
	 }
    public car1(int capacity,int a,int b,int c, User user)
    {
       // this.businessaddress=car.businessaddress;
        //this.capacity=car.capacity;
        //this.cardescription=car.cardescription;
       this.capacity=capacity;
      
       year=c;
        //this.cityname=car.cityname;
        //this.year=car.year;
        /*this.companyname=car.companyname;
        this.model=car.model;
        this.transmission=car.transmission;
        this.kmdriven=car.kmdriven;
        this.vehicletype=car.vehicletype;
        this.marketvalue=car.marketvalue;
        this.color=car.color;
        this.fueltype=car.fueltype;
        this.priceofmonfri=car.priceofmonfri;
        this.priceofsatsun=car.priceofsatsun;
        this.comanyinfo=car.comanyinfo;
        this.website=car.website;
        this.insuranceprovider=car.insuranceprovider;
        this.policynumber=car.policynumber;
        */
        this.user = user;
    }
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


public carfeature getCarfeature() {
		return carfeature;
	}

	public void setCarfeature(carfeature carfeature) {
		this.carfeature = carfeature;
	}

	public String getComanyinfo() {
		return comanyinfo;
	}

	public void setComanyinfo(String comanyinfo) {
		this.comanyinfo = comanyinfo;
	}

	public String getBusinessaddress() {
		return businessaddress;
	}

	public void setBusinessaddress(String businessaddress) {
		this.businessaddress = businessaddress;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getInsuranceprovider() {
		return insuranceprovider;
	}

	public void setInsuranceprovider(String insuranceprovider) {
		this.insuranceprovider = insuranceprovider;
	}

	public String getPolicynumber() {
		return policynumber;
	}

	public void setPolicynumber(String policynumber) {
		this.policynumber = policynumber;
	}

	public String getCarplatenumber() {
		return carplatenumber;
	}

	public void setCarplatenumber(String carplatenumber) {
		this.carplatenumber = carplatenumber;
	}

	/*
	 * public String[] getFeatures() { return features; } 
	 * public void setFeatures(String[] features) { this.features = features; }
	 */
	public String getCardescription() {
		return cardescription;
	}

	public void setCardescription(String cardescription) {
		this.cardescription = cardescription;
	}

	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCar1id() {
		return car1id;
	}

	public void setCar1id(int car1id) {
		this.car1id = car1id;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getKmdriven() {
		return kmdriven;
	}

	public void setKmdriven(String kmdriven) {
		this.kmdriven = kmdriven;
	}

	public String getVehicletype() {
		return vehicletype;
	}

	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}

	public String getMarketvalue() {
		return marketvalue;
	}

	public void setMarketvalue(String marketvalue) {
		this.marketvalue = marketvalue;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getFueltype() {
		return fueltype;
	}

	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}

}