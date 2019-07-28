package com.carapp.carapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carapp.carapplication.model.Account;

public interface AccountRepository  extends JpaRepository<Account,Integer>
{
	@Query("From Account as p WHERE p.user.userid= :customerid")
	public Account findCustomerAccount(@Param("customerid") int customerid);

}
