package com.carapp.carapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carapp.carapplication.model.CustomerCarBooking;
import com.carapp.carapplication.model.CustomerDriverBooking;

public interface CustomerDriverBookingRepository extends JpaRepository<CustomerDriverBooking,Integer>

{

}
