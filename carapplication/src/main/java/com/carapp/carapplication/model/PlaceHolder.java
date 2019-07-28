package com.carapp.carapplication.model;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class PlaceHolder
{
	
	
	private String placeHolderName;
	private String address;
	private double squarefeet;
	private String parkingLocation;
	private String isActive;
	private String inportal;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public CustomerParkingBooking getCustomerparkingbooking() {
		return customerparkingbooking;
	}
	public void setCustomerparkingbooking(CustomerParkingBooking customerparkingbooking) {
		this.customerparkingbooking = customerparkingbooking;
	}
	private Timestamp startTime;
	
	private Timestamp endTime;
	 @OneToOne
	    @JoinColumn(name = "userid")
	  private User user;
	 @OneToOne
	    @JoinColumn(name = "bookingId")
	  private CustomerParkingBooking customerparkingbooking;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int placeHolderId;
	private String idProof;
	private int price;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	private String parkingPhoto;
	public String getIdProof()
	{
		return idProof;
	}
	public void setIdProof(String idProof)
	{
		this.idProof = idProof;
	}
	public String getParkingPhoto() {
		return parkingPhoto;
	}
	public void setParkingPhoto(String parkingPhoto) {
		this.parkingPhoto = parkingPhoto;
	}
	public int getPlaceHolderId() {
		return placeHolderId;
	}
	public void setPlaceHolderId(int placeHolderId) {
		this.placeHolderId = placeHolderId;
	}
	public String getPlaceHolderName() {
		return placeHolderName;
	}
	public void setPlaceHolderName(String placeHolderName) {
		this.placeHolderName = placeHolderName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getSquarefeet() {
		return squarefeet;
	}
	public void setSquarefeet(double squarefeet) {
		this.squarefeet = squarefeet;
	}
	public String getParkingLocation() {
		return parkingLocation;
	}
	public void setParkingLocation(String parkingLocation) {
		this.parkingLocation = parkingLocation;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	
	
	
	

}
