package com.carapp.carapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class CustomerDriverBooking

{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int diverbookingId;
	private int totalprice;
	private int customerid;
	public int getDiverbookingId() {
		return diverbookingId;
	}
	public void setDiverbookingId(int diverbookingId) {
		this.diverbookingId = diverbookingId;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	
	
	
	
	

}
