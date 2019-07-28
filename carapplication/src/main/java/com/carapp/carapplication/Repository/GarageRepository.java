package com.carapp.carapplication.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carapp.carapplication.model.GarageOwner;
public interface GarageRepository  extends JpaRepository<GarageOwner,Integer>{

}
