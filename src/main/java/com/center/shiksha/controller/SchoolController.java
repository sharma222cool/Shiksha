package com.center.shiksha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.center.shiksha.model.School;
import com.center.shiksha.repository.SchoolRepository;

@RestController
public class SchoolController {
	
	@Autowired
	private SchoolRepository schoolRepository;

	@PostMapping("/schools")
	public List<School> saveSchool(@RequestBody School school)
	{
		schoolRepository.save(school);
		
		return schoolRepository.findAll();
	}
	
	@GetMapping("/schools")
	public List<School> getSchools()
	{
		return schoolRepository.findAll();
	}
}
