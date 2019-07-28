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
public class carbooking {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int bookingId;
	@OneToOne
	@JoinColumn(name = "car1id")
	private car1 car1;
		public car1 getCar1() {
		return car1;
	}
	public void setCar1(car1 car1)
	{
		this.car1 = car1;
	}
		private String pickuplocation;
		private String dropofflocation;
		private String pickuptime;
		private  String dropofftime;
		@DateTimeFormat(pattern = "yyyy-mm-dd") 
		private Date pickupdate;
		@DateTimeFormat(pattern = "yyyy-mm-dd") 
		private Date dropoffdate;

		public Date getTemppickupdate() {
			return temppickupdate;
		}
		public void setTemppickupdate(Date temppickupdate) {
			this.temppickupdate = temppickupdate;
		}
		public Date getTempdropoffdate() {
			return tempdropoffdate;
		}
		public void setTempdropoffdate(Date tempdropoffdate) {
			this.tempdropoffdate = tempdropoffdate;
		}
		@DateTimeFormat(pattern = "yyyy-mm-dd") 
		private Date temppickupdate;
		@DateTimeFormat(pattern = "yyyy-mm-dd") 
		private Date tempdropoffdate;

		private String carduration;
		//private int priceMF;//monday -friday
		//private int priceSS;//saturday-sunday
		public int getBookingId() {
			return bookingId;
		}
		public void setBookingId(int bookingId) {
			this.bookingId = bookingId;
		}
		public String getPickuplocation() {
			return pickuplocation;
		}
		public void setPickuplocation(String pickuplocation) {
			this.pickuplocation = pickuplocation;
		}
		public String getDropofflocation() {
			return dropofflocation;
		}
		public void setDropofflocation(String dropofflocation) {
			this.dropofflocation = dropofflocation;
		}
	
		public String getPickuptime() {
			return pickuptime;
		}
		public void setPickuptime(String pickuptime) {
			this.pickuptime = pickuptime;
		}
		public String getDropofftime() {
			return dropofftime;
		}
		public void setDropofftime(String dropofftime) {
			this.dropofftime = dropofftime;
		}
		public Date getPickupdate() {
			return pickupdate;
		}
		public void setPickupdate(Date pickupdate) {
			this.pickupdate = pickupdate;
		}
		public Date getDropoffdate() {
			return dropoffdate;
		}
		public void setDropoffdate(Date dropoffdate) {
			this.dropoffdate = dropoffdate;
		}
		public String getCarduration() {
			return carduration;
		}
		public void setCarduration(String carduration) {
			this.carduration = carduration;
		}
		
		
	/*	public int getPriceMF() {
			return priceMF;
		}
		public void setPriceMF(int priceMF) {
			this.priceMF = priceMF;
		}
		public int getPriceSS() {
			return priceSS;
		}
		public void setPriceSS(int priceSS) {
			this.priceSS = priceSS;
		}
	*/
		

}
