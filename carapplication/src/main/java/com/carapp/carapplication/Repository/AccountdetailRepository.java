package com.carapp.carapplication.Repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.carapp.carapplication.model.AccountDetails;

public interface AccountdetailRepository   extends JpaRepository<AccountDetails,Integer> {

	@Query("FROM AccountDetails WHERE accountno= :accountno")
	AccountDetails findBank(@Param("accountno") BigInteger accountno );
}
