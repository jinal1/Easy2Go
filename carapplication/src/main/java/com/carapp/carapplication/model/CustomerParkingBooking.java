package com.carapp.carapplication.model;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class CustomerParkingBooking {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int parkingid;
	private String Location;
	private String pickupTime;
	private String dropoffTime;
	private Date pickupDate;
	private int totalprice;
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public PlaceHolder getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(PlaceHolder placeholder) {
		this.placeholder = placeholder;
	}
	private Date dropoffDate;
	@OneToOne
	@JoinColumn(name = "car1id")
	private car1 car1;
	@OneToOne
	@JoinColumn(name = "placeHolderId")
	private PlaceHolder placeholder;
	private int customerid;

	public car1 getCar1() {
		return car1;
	}
	public void setCar1(car1 car1) {
		this.car1 = car1;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getParkingid() {
		return parkingid;
	}
	public void setParkingid(int parkingid) {
		this.parkingid = parkingid;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
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
	
	
	

}
