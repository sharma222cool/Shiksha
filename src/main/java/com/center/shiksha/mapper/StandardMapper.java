package com.center.shiksha.mapper;

import org.springframework.stereotype.Component;

import com.center.shiksha.dto.StandardDTO;
import com.center.shiksha.model.Standard;

@Component
public class StandardMapper {
	
	public StandardDTO mapStandardToStandardDTO(Standard standard)
	{
		StandardDTO standardDTO = new StandardDTO();
		standardDTO.setId(standard.getId());
		standardDTO.setName(standard.getName());
		standardDTO.setCode(standard.getCode());
		return standardDTO;
	}
}
