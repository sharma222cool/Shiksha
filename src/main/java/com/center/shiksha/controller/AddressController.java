package com.center.shiksha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.center.shiksha.model.School;
import com.center.shiksha.model.address.Address;
import com.center.shiksha.model.address.City;
import com.center.shiksha.model.address.State;
import com.center.shiksha.repository.SchoolRepository;
import com.center.shiksha.repository.address.AddressRepository;
import com.center.shiksha.repository.address.CityRepository;
import com.center.shiksha.repository.address.StateRepository;

@RestController
public class AddressController {
	
	@Autowired
	private AddressRepository addressRepository;

	@PostMapping("/addresses")
	public List<Address> saveAddress(@RequestBody Address address)
	{
		addressRepository.save(address);
		
		return addressRepository.findAll();
	}
	
	@GetMapping("/addresses")
	public List<Address> getAddresses()
	{
		return addressRepository.findAll();
	}
}
