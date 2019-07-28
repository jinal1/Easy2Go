package com.carapp.carapplication.Repository;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carapp.carapplication.model.CustomerParkingBooking;
import com.carapp.carapplication.model.PlaceHolder;


public interface PlaceHolderRepository extends JpaRepository<PlaceHolder,Integer> {
	@Query("FROM PlaceHolder as p WHERE p.address= :address and p.parkingLocation= :parkingLocation and p.startTime= :starttime and p.endTime= :endtime")
	public List<PlaceHolder> getfilterparkingplace(@Param("address") String address,@Param("parkingLocation") String parkingLocation ,@Param("starttime") Timestamp starttime,@Param("endtime") Timestamp endtime);
	@Query("FROM PlaceHolder as p WHERE p.placeHolderId= :placeholderid")
	public PlaceHolder findbypid(@Param("placeholderid") int placeholderid);
}
