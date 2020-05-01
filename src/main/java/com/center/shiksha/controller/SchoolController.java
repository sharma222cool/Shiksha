package com.center.shiksha.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.center.shiksha.dto.SchoolDTO;
import com.center.shiksha.dto.SectionDTO;
import com.center.shiksha.dto.StandardDTO;
import com.center.shiksha.dto.address.AddressDTO;
import com.center.shiksha.model.School;
import com.center.shiksha.model.SchoolStandardSection;
import com.center.shiksha.model.Section;
import com.center.shiksha.model.Standard;
import com.center.shiksha.model.address.Address;
import com.center.shiksha.model.address.City;
import com.center.shiksha.repository.SchoolRepository;
import com.center.shiksha.repository.SchoolStandardSectionRepository;

@RestController
public class SchoolController {
	
	@Autowired
	private SchoolStandardSectionRepository schoolStandardSectionRepository;

	@PostMapping("/schools")
	public List<SchoolDTO> saveSchool(@RequestBody SchoolDTO schoolDTO)

	{
		List<SchoolStandardSection> schoolSectionStandardList = new ArrayList<>();
		
		School school = new School();
		school.setName(schoolDTO.getName());
		school.setCode(schoolDTO.getCode());
		
		Address address = new Address();
		AddressDTO addressDTO = schoolDTO.getAddress();
		address.setStreet1(addressDTO.getStreet1());
		address.setStreet2(addressDTO.getStreet2());
		address.setPincode(addressDTO.getPincode());
		
		City city = new City();
		city.setId(addressDTO.getCity().getId());
		address.setCity(city);
		
		school.setAddress(address);
		
		List<StandardDTO> standards = schoolDTO.getStandards();
		for(StandardDTO standardDTO : standards)
		{
			Standard standard = new Standard();
			standard.setId(standardDTO.getId());
			
			List<SectionDTO> sections = standardDTO.getSections();
			for(SectionDTO sectionDTO: sections)
			{
				Section section = new Section();
				section.setId(sectionDTO.getId());
				
				SchoolStandardSection schoolStandardSection = new SchoolStandardSection();
				schoolStandardSection.setSchool(school);
				schoolStandardSection.setStandard(standard);
				schoolStandardSection.setSection(section);
				
				schoolSectionStandardList.add(schoolStandardSection);
			}
		}
		
		schoolStandardSectionRepository.saveAll(schoolSectionStandardList);
		
		return getSchools();
	}
	
	@GetMapping("/schools")
	public List<SchoolDTO> getSchools()
	{
		List<SchoolStandardSection> joinedList = schoolStandardSectionRepository.findAll();
		
		List<SchoolDTO> schoolDTOList = new ArrayList<>();
		for(SchoolStandardSection schoolStandardSection: joinedList)
		{
			School school = schoolStandardSection.getSchool();
			Standard standard = schoolStandardSection.getStandard();
			Section section = schoolStandardSection.getSection();
			
			SchoolDTO schoolDTO = new SchoolDTO();
			schoolDTO.setId(school.getId());
			
			if(schoolDTOList.contains(schoolDTO))
			{
				SchoolDTO existingSchoolDTO = schoolDTOList.get(schoolDTOList.indexOf(schoolDTO));
				configureExistingSchool(schoolDTOList, standard, section, existingSchoolDTO);
			}else {
				configureNewSchool(schoolDTOList, school, standard, section, schoolDTO);
			}
		}
		
		return schoolDTOList;
	}

	private void configureNewSchool(List<SchoolDTO> schoolDTOList, School school, Standard standard, Section section,
			SchoolDTO schoolDTO) {
		schoolDTO.setName(school.getName());
		schoolDTO.setCode(school.getCode());
		
		StandardDTO standardDTO = getStandardDTO(standard);
		SectionDTO sectionDTO = getSectionDTO(section);
		
		standardDTO.setSections(Arrays.asList(sectionDTO));
		
		schoolDTO.setStandards(Arrays.asList(standardDTO));
		
		schoolDTOList.add(schoolDTO);
	}

	private void configureExistingSchool(List<SchoolDTO> schoolDTOList, Standard standard, Section section,
			SchoolDTO schoolDTO) {
		StandardDTO standarDTO = new StandardDTO();
		standarDTO.setId(standard.getId());
		
		List<StandardDTO> existingStandards = schoolDTO.getStandards();
		System.out.println("school_id : "+schoolDTO.getName()+" standardDTO id :"+standarDTO.getId()+" existing standards :"+existingStandards);
		if(existingStandards.contains(standarDTO))
		{
			
			StandardDTO existingStandarDTO = existingStandards.get(existingStandards.indexOf(standarDTO));
			SectionDTO sectionDTO = getSectionDTO(section);
			
			List<SectionDTO> sections = new ArrayList<SectionDTO>();
			sections.addAll(existingStandarDTO.getSections());
			sections.add(sectionDTO);
			
			existingStandarDTO.setSections(sections);
			
			schoolDTOList.get(schoolDTOList.indexOf(schoolDTO)).setStandards(existingStandards);
			
		}else {
			
			StandardDTO standardDTO = getStandardDTO(standard);
			SectionDTO sectionDTO = getSectionDTO(section);
			
			standardDTO.setSections(Arrays.asList(sectionDTO));
			
			List<StandardDTO> standards = new ArrayList<StandardDTO>();
			standards.addAll(existingStandards);
			standards.add(standardDTO);
			
			schoolDTO.setStandards(existingStandards);
			
			schoolDTOList.get(schoolDTOList.indexOf(schoolDTO)).setStandards(standards);
			
		}
	}

	private SectionDTO getSectionDTO(Section section) {
		SectionDTO sectionDTO = new SectionDTO();
		sectionDTO.setId(section.getId());
		sectionDTO.setName(section.getName());
		return sectionDTO;
	}

	private StandardDTO getStandardDTO(Standard standard) {
		StandardDTO standardDTO = new StandardDTO();
		standardDTO.setId(standard.getId());
		standardDTO.setName(standard.getName());
		standardDTO.setCode(standard.getCode());
		return standardDTO;
	}

}
