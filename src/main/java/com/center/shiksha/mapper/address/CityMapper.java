package com.center.shiksha.mapper.address;

import org.springframework.stereotype.Component;

import com.center.shiksha.dto.address.CityDTO;
import com.center.shiksha.model.address.City;

@Component
public class CityMapper {
	
	public CityDTO mapCityToCityDTO(City city)
	{
		CityDTO cityDTO = new CityDTO();
		
		cityDTO.setId(city.getId());
		cityDTO.setLatitude((city.getLatitude()));
		cityDTO.setLongitude(city.getLongitude());
		cityDTO.setName(city.getName());
		
		return cityDTO;
	}
}
