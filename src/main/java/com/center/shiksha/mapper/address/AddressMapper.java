package com.center.shiksha.mapper.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.center.shiksha.dto.address.AddressDTO;
import com.center.shiksha.model.address.Address;
import com.center.shiksha.model.address.City;

@Component
public class AddressMapper {
	
	@Autowired
	CityMapper cityMapper;
	@Autowired
	StateMapper stateMapper;
	
	public AddressDTO mapAddressToAddressDTO(Address address)
	{
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setId(address.getId());
		addressDTO.setStreet1(address.getStreet1());
		addressDTO.setStreet2(address.getStreet2());
		addressDTO.setPincode(address.getPincode());
		setCity(addressDTO, address);
		addressDTO.setCountry(address.getCity().getState().getCountry().getName());
		return addressDTO;
	}
	
	private void setCity(AddressDTO addressDTO, Address address) {
		City city = address.getCity();
		addressDTO.setCity(cityMapper.mapCityToCityDTO(city));
		addressDTO.setState(stateMapper.mapStateToStateDTO(city.getState()));
	}

	public Address mapAddressDTOToAddress(AddressDTO addressDTO) {
		
		Address address = new Address();
		address.setStreet1(addressDTO.getStreet1());
		address.setStreet2(addressDTO.getStreet2());
		address.setPincode(addressDTO.getPincode());
		
		City city = new City();
		city.setId(addressDTO.getCity().getId());
		address.setCity(city);
		
		return address;
	}
}
