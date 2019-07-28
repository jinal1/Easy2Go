package com.carapp.carapplication.DTO;

import com.carapp.carapplication.model.car1;
public class CarDto {
	private car1 car;
	
	private String imagepath;
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	private byte[] b;
	public car1 getCar() {
		return car;
	}
	public void setCar(car1 car) {
		this.car = car;
	}
	public byte[] getB() {
		return b;
	}
	public void setB(byte[] b) 
	
	{
		this.b = b;
	}

	

}
