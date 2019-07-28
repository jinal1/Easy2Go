package com.carapp.carapplication.service;

import java.sql.Date;
import java.util.List;

import com.carapp.carapplication.DTO.CarDto;
import com.carapp.carapplication.model.car1;
import com.carapp.carapplication.model.carbooking;



public interface CarService
{
	List<CarDto>  findAllAuthFcar();
	List<CarDto>  findAllAuthcar();
	CarDto findOnecar(int id);
	void getcarid(carbooking carbooking);
	List<CarDto>  findThreeCar(String a,String b,String c);
	List<CarDto> findHistoryCar(int id);
	List<CarDto> getsearchcars(carbooking carbooking);
	List<CarDto> getfiltercars(String location,String brand,String model,int year,int price);
List<Date> getcarbookingdate(int id);
}
