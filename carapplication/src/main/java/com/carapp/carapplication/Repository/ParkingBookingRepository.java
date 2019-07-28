package com.carapp.carapplication.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carapp.carapplication.model.CustomerParkingBooking;

public interface ParkingBookingRepository extends JpaRepository<CustomerParkingBooking,Integer> 
{
	@Query("FROM CustomerParkingBooking as p WHERE p.placeholder.placeHolderId= :placeholderid")
	public CustomerParkingBooking findbyplaceholderid(@Param("placeholderid") int placeholderid);


}
