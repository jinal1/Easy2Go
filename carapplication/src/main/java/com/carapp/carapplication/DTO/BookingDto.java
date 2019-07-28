package com.carapp.carapplication.DTO;
import com.carapp.carapplication.model.carbooking;
public class BookingDto
{
private  carbooking carbooking;
private String ownername;
public carbooking getCarbooking()
{
	return carbooking;
}
public void setCarbooking(carbooking carbooking) 
{
	this.carbooking = carbooking;
}
public String getOwnername() {
	return ownername;
}
public void setOwnername(String ownername)
{
	this.ownername = ownername;
}

}
