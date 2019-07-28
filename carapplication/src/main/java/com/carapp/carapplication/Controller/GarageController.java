package com.carapp.carapplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carapp.carapplication.Repository.GarageRepository;

import com.carapp.carapplication.model.GarageOwner;
import com.carapp.carapplication.model.User;


@Controller
public class GarageController 
{

	@Autowired 
	GarageRepository gR;

	 @RequestMapping(value = "/garage", method = RequestMethod.GET)
	  @ResponseBody
	    public ResponseEntity<List<GarageOwner>> listAllGarages() 
	  {
		  System.out.println("helllooooooooooooooooooooooooooo");
		  List<GarageOwner> Garages=gR.findAll();
	        if(Garages.isEmpty())
	        {
	            return new ResponseEntity<List<GarageOwner>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<GarageOwner>>(Garages, HttpStatus.OK);
	    }
	

	
	@RequestMapping(value = "/saveGarage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<GarageOwner>  savemethod(@RequestBody GarageOwner garageowner)
	{
		
	
		
		
	
	    gR.save(garageowner );
			
			
		
	return new ResponseEntity<GarageOwner>(HttpStatus.OK);
	
	}
	
	
	
}
