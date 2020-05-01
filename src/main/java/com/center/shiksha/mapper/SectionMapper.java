package com.center.shiksha.mapper;

import org.springframework.stereotype.Component;

import com.center.shiksha.dto.SectionDTO;
import com.center.shiksha.model.Section;

@Component
public class SectionMapper {
	
	public SectionDTO mapSectionToSectionDTO(Section section)
	{
		SectionDTO sectionDTO = new SectionDTO();
		sectionDTO.setId(section.getId());
		sectionDTO.setName(section.getName());
		return sectionDTO;
	}
}
