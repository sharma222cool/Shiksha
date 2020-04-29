package com.center.shiksha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.center.shiksha.model.School;
import com.center.shiksha.model.Standard;
import com.center.shiksha.model.address.Address;
import com.center.shiksha.model.address.City;
import com.center.shiksha.model.address.State;
import com.center.shiksha.repository.SchoolRepository;
import com.center.shiksha.repository.StandardRepository;
import com.center.shiksha.repository.address.AddressRepository;
import com.center.shiksha.repository.address.CityRepository;
import com.center.shiksha.repository.address.StateRepository;

@RestController
public class StandardController {
	
	@Autowired
	private StandardRepository standardRepository;

	@PostMapping("/standards")
	public List<Standard> saveStandard(@RequestBody Standard standard)
	{
		System.out.println(standard);
		standardRepository.save(standard);
		
		return standardRepository.findAll();
	}
	
	@GetMapping("/standards")
	public List<Standard> getStandards()
	{
		return standardRepository.findAll();
	}
}
