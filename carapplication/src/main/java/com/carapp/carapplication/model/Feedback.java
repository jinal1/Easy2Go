package com.carapp.carapplication.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feedback

{

	private String name;
	
	private String emailId;
	private String city;
	
	private int bookingId;
	private String Comment;
	
	public String getName() {
		return name;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int feedbackId;
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	
	private String message;
	public String getMessage() {
		return message;

	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRatecar() {
		return ratecar;
	}
	public void setRatecar(String ratecar) {
		this.ratecar = ratecar;
	}
	public String getRatedriver() {
		return ratedriver;
	}
	public void setRatedriver(String ratedriver) {
		this.ratedriver = ratedriver;
	}


	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getComment() {
		return Comment;
	}
	public String getRateparking() {
		return rateparking;
	}
	public void setRateparking(String rateparking) {
		this.rateparking = rateparking;
	}
	private String ratecar;
	private String ratedriver;
	private String rateparking;

	
	public void setName(String name) {
		this.name = name;
	}



	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	
}
