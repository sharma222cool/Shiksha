package com.center.shiksha.controller.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.center.shiksha.model.School;
import com.center.shiksha.model.address.City;
import com.center.shiksha.model.address.State;
import com.center.shiksha.repository.SchoolRepository;
import com.center.shiksha.repository.address.CityRepository;
import com.center.shiksha.repository.address.StateRepository;

@RestController
public class CityController {
	
	@Autowired
	private CityRepository cityRepository;

	@PostMapping("/cities")
	public List<City> saveCity(@RequestBody City city)
	{
		cityRepository.save(city);
		
		return cityRepository.findAll();
	}
	
	@GetMapping("/cities")
	public List<City> saveSchool()
	{
		return cityRepository.findAll();
	}
}
