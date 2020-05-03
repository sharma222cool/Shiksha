package com.center.shiksha.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.center.shiksha.dto.SchoolDTO;
import com.center.shiksha.dto.SectionDTO;
import com.center.shiksha.dto.StandardDTO;
import com.center.shiksha.mapper.SectionMapper;
import com.center.shiksha.mapper.StandardMapper;
import com.center.shiksha.mapper.address.AddressMapper;
import com.center.shiksha.model.School;
import com.center.shiksha.model.SchoolStandardSection;
import com.center.shiksha.model.Section;
import com.center.shiksha.model.Standard;
import com.center.shiksha.repository.SchoolStandardSectionRepository;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class SchoolController {
	
	@Autowired
	private SchoolStandardSectionRepository schoolStandardSectionRepository;
	@Autowired
	SectionMapper sectionMapper;
	@Autowired
	StandardMapper standardMapper;
	@Autowired
	AddressMapper addressMapper;

	@PostMapping("/schools")
	public List<SchoolStandardSection> saveSchool(@RequestBody SchoolDTO schoolDTO)

	{
		List<SchoolStandardSection> schoolSectionStandardList = new ArrayList<>();
		
		School school = new School();
		school.setName(schoolDTO.getName());
		school.setCode(schoolDTO.getCode());
		
		school.setAddress(addressMapper.mapAddressDTOToAddress(schoolDTO.getAddress()));
		
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
		
		return schoolStandardSectionRepository.findAll();
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
				configureExistingSchool(schoolDTOList, standard, section, schoolDTO);
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
		
		schoolDTO.setAddress(addressMapper.mapAddressToAddressDTO(school.getAddress()));
		
		StandardDTO standardDTO = standardMapper.mapStandardToStandardDTO(standard);
		SectionDTO sectionDTO = sectionMapper.mapSectionToSectionDTO(section);
		
		standardDTO.setSections(Arrays.asList(sectionDTO));
		
		schoolDTO.setStandards(Arrays.asList(standardDTO));
		
		schoolDTOList.add(schoolDTO);
	}

	private void configureExistingSchool(List<SchoolDTO> schoolDTOList, Standard standard, Section section,
			SchoolDTO schoolDTO) {
		
		SchoolDTO existingSchoolDTO = schoolDTOList.get(schoolDTOList.indexOf(schoolDTO));
		StandardDTO standarDTO = new StandardDTO();
		standarDTO.setId(standard.getId());
		
		List<StandardDTO> existingStandards = existingSchoolDTO.getStandards();
	
		if(existingStandards.contains(standarDTO))
		{
			configureExistingStandard(schoolDTOList, section, existingSchoolDTO, standarDTO, existingStandards);
		}else {
			configureNewStandard(schoolDTOList, standard, section, existingSchoolDTO, existingStandards);
		}
	}

	private void configureExistingStandard(List<SchoolDTO> schoolDTOList, Section section, SchoolDTO existingSchoolDTO,
			StandardDTO standarDTO, List<StandardDTO> existingStandards) {
		StandardDTO existingStandarDTO = existingStandards.get(existingStandards.indexOf(standarDTO));
		SectionDTO sectionDTO = sectionMapper.mapSectionToSectionDTO(section);
		
		List<SectionDTO> sections = new ArrayList<SectionDTO>();
		sections.addAll(existingStandarDTO.getSections());
		sections.add(sectionDTO);
		
		existingStandarDTO.setSections(sections);
		
		schoolDTOList.get(schoolDTOList.indexOf(existingSchoolDTO)).setStandards(existingStandards);
	}

	private void configureNewStandard(List<SchoolDTO> schoolDTOList, Standard standard, Section section,
			SchoolDTO existingSchoolDTO, List<StandardDTO> existingStandards) {
		StandardDTO standardDTO = standardMapper.mapStandardToStandardDTO(standard);
		SectionDTO sectionDTO = sectionMapper.mapSectionToSectionDTO(section);
		
		standardDTO.setSections(Arrays.asList(sectionDTO));
		
		List<StandardDTO> standards = new ArrayList<StandardDTO>();
		standards.addAll(existingStandards);
		standards.add(standardDTO);
		
		existingSchoolDTO.setStandards(existingStandards);
		
		schoolDTOList.get(schoolDTOList.indexOf(existingSchoolDTO)).setStandards(standards);
	}
}
