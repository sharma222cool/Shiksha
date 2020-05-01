package com.center.shiksha.dto;

import java.util.ArrayList;
import java.util.List;

import com.center.shiksha.dto.address.AddressDTO;

public class SchoolDTO {
	
	private int id;
	private String name;
	private String code;
	private AddressDTO address;
	private List<StandardDTO> standards = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	public List<StandardDTO> getStandards() {
		return standards;
	}
	public void setStandards(List<StandardDTO> standards) {
		this.standards = standards;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolDTO other = (SchoolDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
