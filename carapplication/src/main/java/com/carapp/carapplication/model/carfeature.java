package com.carapp.carapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class carfeature 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String evhybrid;
	private String bikerack;
	private String allwheeldrive;
	private String childseat;
	private String snowtires;
	public String getAllwheeldrive() {
		return allwheeldrive;
	}
	public void setAllwheeldrive(String allwheeldrive) {
		this.allwheeldrive = allwheeldrive;
	}
	public String getChildseat() {
		return childseat;
	}
	public void setChildseat(String childseat) {
		this.childseat = childseat;
	}
	public String getSnowtires() {
		return snowtires;
	}
	public void setSnowtires(String snowtires) {
		this.snowtires = snowtires;
	}
	public String getSkirack() {
		return skirack;
	}
	public void setSkirack(String skirack) {
		this.skirack = skirack;
	}
	public String getBluetooth() {
		return bluetooth;
	}
	public void setBluetooth(String bluetooth) {
		this.bluetooth = bluetooth;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getUsbinput() {
		return usbinput;
	}
	public void setUsbinput(String usbinput) {
		this.usbinput = usbinput;
	}
	public String getHeatedseats() {
		return heatedseats;
	}
	public void setHeatedseats(String heatedseats) {
		this.heatedseats = heatedseats;
	}
	public String getAudioinput() {
		return audioinput;
	}
	public void setAudioinput(String audioinput) {
		this.audioinput = audioinput;
	}
	public String getConvertible() {
		return convertible;
	}
	public void setConvertible(String convertible) {
		this.convertible = convertible;
	}
	public String getPetfriendly() {
		return petfriendly;
	}
	public void setPetfriendly(String petfriendly) {
		this.petfriendly = petfriendly;
	}
	public String getTollpass() {
		return tollpass;
	}
	public void setTollpass(String tollpass) {
		this.tollpass = tollpass;
	}
	public String getSunroof() {
		return sunroof;
	}
	public void setSunroof(String sunroof) {
		this.sunroof = sunroof;
	}
	public String getEvhybrid() {
		return evhybrid;
	}
	public String getBikerack() {
		return bikerack;
	}
	private String skirack;
	private String bluetooth;
	private String gps;
	private String usbinput;
	private String heatedseats;
	private String audioinput;
	private String convertible;
	private String petfriendly;
	private String tollpass;
	private String sunroof;
	
	
	
	

	
	public String isEvhybrid() {
		return evhybrid;
	}
	public void setEvhybrid(String evhybrid) {
		this.evhybrid = evhybrid;
	}
	public String isBikerack() {
		return bikerack;
	}
	public void setBikerack(String bikerack) {
		this.bikerack = bikerack;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


}
