package com.center.shiksha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.center.shiksha.model.School;
import com.center.shiksha.model.address.State;
import com.center.shiksha.repository.SchoolRepository;
import com.center.shiksha.repository.address.StateRepository;

@RestController
public class StateController {
	
	@Autowired
	private StateRepository stateRepository;

	@PostMapping("/states")
	public List<State> saveSchool(@RequestBody State state)
	{
		stateRepository.save(state);
		
		return stateRepository.findAll();
	}
	
	@GetMapping("/states")
	public List<State> saveSchool()
	{
		return stateRepository.findAll();
	}
}
