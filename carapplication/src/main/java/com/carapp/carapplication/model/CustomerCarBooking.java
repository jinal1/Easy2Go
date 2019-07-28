package com.carapp.carapplication.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class CustomerCarBooking 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bookingId;
	private String pickupLocation;
	private String dropoffLocation;
	private String pickupTime;
	private int totalprice;
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public List<car1> getCar1() {
		return car1;
	}
	public void setCar1(List<car1> car1) {
		this.car1 = car1;
	}
	private String dropoffTime;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date pickupDate;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date dropoffDate;
	//private Driver driver;
	private String carDuration;
	private String driverRequired;
	private String customerLicense;
	private String customerCoLicense;
	@JsonIgnore
	@OneToMany(mappedBy = "custcarbook",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	
	 private List<car1> car1;
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	private int customerid;

	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getPickupLocation() {
		return pickupLocation;
	}
	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	public String getDropoffLocation() {
		return dropoffLocation;
	}
	public void setDropoffLocation(String dropoffLocation) {
		this.dropoffLocation = dropoffLocation;
	}
	public String getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}
	public String getDropoffTime() {
		return dropoffTime;
	}
	public void setDropoffTime(String dropoffTime) {
		this.dropoffTime = dropoffTime;
	}
	public Date getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}
	public Date getDropoffDate() {
		return dropoffDate;
	}
	public void setDropoffDate(Date dropoffDate) {
		this.dropoffDate = dropoffDate;
	}
	public String getCarDuration() {
		return carDuration;
	}
	public void setCarDuration(String carDuration) {
		this.carDuration = carDuration;
	}
	public String getDriverRequired() {
		return driverRequired;
	}
	public void setDriverRequired(String driverRequired) {
		this.driverRequired = driverRequired;
	}
	public String getCustomerLicense() {
		return customerLicense;
	}
	public void setCustomerLicense(String customerLicense) {
		this.customerLicense = customerLicense;
	}
	public String getCustomerCoLicense() {
		return customerCoLicense;
	}
	public void setCustomerCoLicense(String customerCoLicense) {
		this.customerCoLicense = customerCoLicense;
	}
	
	
}
