package com.carapp.carapplication.model;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bank {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private long bankId;
	
	private String bankname;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "bankObj", cascade = CascadeType.ALL)
	private Set<AccountDetails> accList;

	public long getBankId() {
		return bankId;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	
	

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	

	public Set<AccountDetails> getAccList() {
		return accList;
	}

	public void setAccList(Set<AccountDetails> accList) {
		this.accList = accList;
	} 
}
