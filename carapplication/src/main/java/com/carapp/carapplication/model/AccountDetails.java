package com.carapp.carapplication.model;


import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class AccountDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int accountdetailId;

	private BigInteger accountno;
	public int getAccountdetailId() {
		return accountdetailId;
	}



	public void setAccountdetailId(int accountdetailId) {
		this.accountdetailId = accountdetailId;
	}



	public BigInteger getAccountno() {
		return accountno;
	}



	public void setAccountno(BigInteger accountno) {
		this.accountno = accountno;
	}



	public int getBalance() {
		return balance;
	}



	public void setBalance(int balance) {
		this.balance = balance;
	}



	public Bank getBankObj() {
		return bankObj;
	}



	public void setBankObj(Bank bankObj) {
		this.bankObj = bankObj;
	}



	private int balance;
	
	public AccountDetails()
	{}
	
	
	
	@ManyToOne
	@JoinColumn(name = "bankId")
	private Bank bankObj;
	

	
    
	
}
