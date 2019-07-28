package com.carapp.carapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class garage
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int garageownerid;
	private String garagelocation;
	private String[] serviceprovided;
	private String[] speciallity;
	private String timingofgarage;
	public int getGarageownerid() 
	{
		return garageownerid;
	}
	public void setGarageownerid(int garageownerid) {
		this.garageownerid = garageownerid;
	}
	public String getGaragelocation() {
		return garagelocation;
	}
	public void setGaragelocation(String garagelocation) {
		this.garagelocation = garagelocation;
	}
	public String[] getServiceprovided() {
		return serviceprovided;
	}
	public void setServiceprovided(String[] serviceprovided) {
		this.serviceprovided = serviceprovided;
	}
	public String[] getSpeciallity() {
		return speciallity;
	}
	public void setSpeciallity(String[] speciallity) {
		this.speciallity = speciallity;
	}
	public String getTimingofgarage() {
		return timingofgarage;
	}
	public void setTimingofgarage(String timingofgarage) {
		this.timingofgarage = timingofgarage;
	}
	

}
