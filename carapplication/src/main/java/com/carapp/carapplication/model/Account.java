package com.carapp.carapplication.model;


import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int accountId;
	@OneToOne
	  @JoinColumn(name = "userid")
	  private User user;
	
	private BigInteger accountno;
	public void setAccountno(BigInteger accountno) {
		this.accountno = accountno;
	}

	private String security_code;
	private int balance;
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}



	

	@ManyToOne
	@JoinColumn(name = "bankId")
	private Bank bankObj;

	

	public Bank getBankObj() {
		return bankObj;
	}

	public void setBankObj(Bank bankObj) {
		this.bankObj = bankObj;
	}

	

 

	

	

	

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	
	

	

	public BigInteger getAccountno() {
		return accountno;
	}

	

	
}
