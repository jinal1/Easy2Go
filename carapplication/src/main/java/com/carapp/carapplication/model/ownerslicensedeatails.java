package com.carapp.carapplication.model;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class ownerslicensedeatails 
{

	@Id
 	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ownerid;
	private String licensenumber;
	private String firstname;
	private String middlename;
	private String lastname;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date dateofbirth;
	private String issuingstate;
	public int getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}
	public String getLicensenumber() {
		return licensenumber;
	}
	public void setLicensenumber(String licensenumber) {
		this.licensenumber = licensenumber;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() 
	{
		return middlename;
	}
	public void setMiddlename(String middlename)
	{
		this.middlename = middlename;
	}
	public String getLastname()
	{
		return lastname;
	}
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	public Date getDateofbirth() 
	{
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getIssuingstate() {
		return issuingstate;
	}
	public void setIssuingstate(String issuingstate) {
		this.issuingstate = issuingstate;
	}
}
