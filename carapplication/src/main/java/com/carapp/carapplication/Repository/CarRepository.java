package com.carapp.carapplication.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.carapp.carapplication.model.car1;
public interface CarRepository extends JpaRepository<car1,Integer>
{
@Query("FROM car1 as p WHERE p.companyname= :a1 or p.companyname= :b or p.companyname= :c1 and p.isActive= :Active and p.isAuthenticated= :Auth and p.inPortal= :Portal")
public List<car1> threeCars(@Param("a1") String a1,@Param("b") String b,@Param("c1") String c1,@Param("Auth") String Auth ,@Param("Active") String Active,@Param("Portal") String Portal);
@Query("From car1 as p WHERE p.isAuthenticated= :ast and p.isActive= :bst and p.inPortal= :ast")
public List<car1> findAuth(@Param("ast") String ast,@Param("bst") String bst);

@Query("From car1 as p WHERE p.isAuthenticated= :bst")
public List<car1> findFcar(@Param("bst") String bst);
@Query("From car1 as p WHERE  p.user.userid= :id1  and  p.isAuthenticated= :ast")
public List<car1> findHistorycar(@Param("id1") int id1,@Param("ast") String ast);
@Query("From car1 as p WHERE  p.cityname= :location  and p.companyname= :brand and p.model= :model and p.companyname= :brand and  p.year= :year and  p.price= :price and p.isAuthenticated= :is")
public List<car1> findfiltercar(@Param("location") String location,@Param("brand") String brand,@Param("model") String model,@Param("year") int year,@Param("price") int price,@Param("is") String is);
@Query("From car1 as p WHERE p.car1id= :id")
public car1 finduser(@Param("id") int id);
@Query("From car1 as p WHERE p.custcarbook.bookingId = :carbookingid")
public car1 findcarid(@Param("carbookingid") int carbookingid);


}

