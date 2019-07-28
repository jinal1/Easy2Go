package com.carapp.carapplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carapp.carapplication.model.CarImages;

public interface CarImageRepository extends JpaRepository<CarImages,Integer>{
	 @Query("FROM CarImages  WHERE car1id = :car1id")
	    public 	List<CarImages> findimagepath(@Param("car1id") int car1id);
	 @Query(value="Select p from CarImages p where car1id= :car1id" ,nativeQuery=true)
	 public CarImages findoneimagepath(@Param("car1id") int car1id);
	 
}
