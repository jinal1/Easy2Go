package com.carapp.carapplication.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Driver
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int driverId;
	private String drivername;
	private int price;
	private String isActive;
	private String inportal;
	//private String name;
	//private int MobileNu;
	//private Date dob;
	//private String gender;
	//private String address;
	
	public String getInportal() {
		return inportal;
	}
	public void setInportal(String inportal) {
		this.inportal = inportal;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	private int experience;
	@DateTimeFormat(pattern = "yyyy-mm-dd") 
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date endDate;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	private String license;
	private String vehicalType;
	@OneToOne
	@JoinColumn(name="bookingId")
	private CustomerCarBooking customercarbooking;

	
	public int getExperience() {
		return experience;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getVehicalType() {
		return vehicalType;
	}
	public void setVehicalType(String vehicalType) {
		this.vehicalType = vehicalType;
	}
	public CustomerCarBooking getCustomercarbooking() {
		return customercarbooking;
	}
	public void setCustomercarbooking(CustomerCarBooking customercarbooking) {
		this.customercarbooking = customercarbooking;
	}
	
	

	

}
