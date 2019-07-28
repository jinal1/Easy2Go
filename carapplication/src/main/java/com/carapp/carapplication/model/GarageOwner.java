package com.carapp.carapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GarageOwner {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int garageownerid;
	public int getGarageownerid() {
		return garageownerid;
	}
	public void setGarageownerid(int garageownerid)
	{
		this.garageownerid = garageownerid;
	}
	private String ownerName;
	private String locationOfgarage;
	private String serviceprovided;
	private String starttime;
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	private String endtime;
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getLocationOfgarage() {
		return locationOfgarage;
	}
	public void setLocationOfgarage(String locationOfgarage) {
		this.locationOfgarage = locationOfgarage;
	}
	public String getServiceprovided() {
		return serviceprovided;
	}
	public void setServiceprovided(String serviceprovided) {
		this.serviceprovided = serviceprovided;
	}
	

	

}
