package com.carapp.carapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carapp.carapplication.model.carfeature;

public interface carfeatureRepository extends JpaRepository<carfeature,Integer>
{

}
