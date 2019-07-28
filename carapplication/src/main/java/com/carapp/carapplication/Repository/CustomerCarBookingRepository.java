package com.carapp.carapplication.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carapp.carapplication.model.CustomerCarBooking;
public interface CustomerCarBookingRepository extends JpaRepository<CustomerCarBooking,Integer>{
	@Query("From CustomerCarBooking as p WHERE p.customerid= :userid")
	public CustomerCarBooking findBooking(@Param("userid") int userid);
}
