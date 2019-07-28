package com.carapp.carapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carapp.carapplication.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> 
{
	  public User findByUserName(String userName);
	  @Query("FROM User  as u WHERE u.userName= :drivername")
	  public User findmailId(@Param("drivername") String drivername);
	
}
