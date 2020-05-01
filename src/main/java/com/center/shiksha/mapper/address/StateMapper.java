package com.center.shiksha.mapper.address;

import org.springframework.stereotype.Component;

import com.center.shiksha.dto.address.CityDTO;
import com.center.shiksha.dto.address.StateDTO;
import com.center.shiksha.model.address.City;
import com.center.shiksha.model.address.State;

@Component
public class StateMapper {
	
	public StateDTO mapStateToStateDTO(State state)
	{
		StateDTO stateDTO = new StateDTO();
		stateDTO.setName(state.getName());
		stateDTO.setCode(state.getCode());
		
		return stateDTO;
	}
}
