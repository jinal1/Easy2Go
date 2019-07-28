package com.carapp.carapplication.Repository;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.carapp.carapplication.model.Driver;
public interface DriverRepository extends JpaRepository<Driver,Integer>
{
	@Query("FROM Driver WHERE customercarbooking.bookingId= :customercarbookingid")
	public Driver getDriver(@Param("customercarbookingid") int customercarbookingid);
}
