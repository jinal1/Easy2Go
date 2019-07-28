package com.carapp.carapplication.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class parkingbooking {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int parkingbookingid;
	private String parkingarea;
	private Timestamp reachingtime;
	public Timestamp getReachingtime() {
		return reachingtime;
	}
	public void setReachingtime(Timestamp reachingtime) {
		this.reachingtime = reachingtime;
	}
	public Timestamp getLeavingtime() {
		return leavingtime;
	}
	public void setLeavingtime(Timestamp leavingtime) {
		this.leavingtime = leavingtime;
	}
	private Timestamp leavingtime;
	
	@OneToOne
	@JoinColumn(name = "placeHolderId")
	private PlaceHolder placeholder;
	

	public PlaceHolder getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(PlaceHolder placeholder) {
		this.placeholder = placeholder;
	}


	public int getParkingbookingid() {
		return parkingbookingid;
	}
	public void setParkingbookingid(int parkingbookingid) {
		this.parkingbookingid = parkingbookingid;
	}
	public String getParkingarea() {
		return parkingarea;
	}
	public void setParkingarea(String parkingarea) {
		this.parkingarea = parkingarea;
	}

	
	
	
	
	

}
