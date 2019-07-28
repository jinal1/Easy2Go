package com.carapp.carapplication.model;
import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
@Entity
public class User
{
	@Id
 	@GeneratedValue(strategy=GenerationType.AUTO)
		private int userid;

        private String userName;
	    private String password;
		private String emailid;
		private String mobilenumber;
		private String confirmpassword;
		private int enabled;
		
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
		 private Set<car1> cars;
	
		public String getUsername() {
			return userName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		public Set<car1> getCars() {
			return cars;
		}

		public void setCars(Set<car1> cars) {
			this.cars = cars;
		}

		public void setUsername(String username) {
			this.userName = username;
		}

		public int getEnabled() {
			return enabled;
		}

		public void setEnabled(int enabled) {
			this.enabled = enabled;
		}
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
		public String getEmailid() {
			return emailid;
		}
		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}
		public String getMobilenumber() {
			return mobilenumber;
		}
		public void setMobilenumber(String mobilenumber) {
			this.mobilenumber = mobilenumber;
		}
		public String getConfirmpassword() {
			return confirmpassword;
		}
		public void setConfirmpassword(String confirmpassword) {
			this.confirmpassword = confirmpassword;
		}
		public User()
		{
			
		}
		public User(User user) 
		{
	        this.userid = user.userid;
	        
	             
	        this.password = user.password;
	        this.emailid=user.emailid;
	        this.mobilenumber=user.mobilenumber;
	        this.confirmpassword=user.confirmpassword;
	        this.enabled=user.enabled;  
	        this.userName=user.userName;
	   }
	
		
		private Date dob;
		//private String gender;
		
		private String city;
		private String address;
		
		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Date getDob() {
			return dob;
		}
		public void setDob(Date dob) {
			this.dob = dob;
		}
		/*public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}*/
		
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
	
	
	
		
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	
		

	

}
