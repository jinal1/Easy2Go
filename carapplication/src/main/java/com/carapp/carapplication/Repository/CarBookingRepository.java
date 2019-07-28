package com.carapp.carapplication.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.carapp.carapplication.model.carbooking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarBookingRepository extends JpaRepository<carbooking,Integer> {
	//@Query(value="Select car1id from carbooking p where pickupdate= :pickupdate,dropoffdate= :dropoffdate,pickuptime= :pickuptime" ,nativeQuery=true)
	 //public 	List<Integer> findcarids(@Param("pickupdate") Date pickupdate,@Param("dropoffdate") Date dropoffdate,@Param("pickuptime") Time pickuptime);

	
	@Query("FROM carbooking WHERE car1id= :carid")
	public carbooking getbookingdata(@Param("carid") int carid );
	
}
