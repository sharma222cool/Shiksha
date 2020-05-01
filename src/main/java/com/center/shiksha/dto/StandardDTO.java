package com.center.shiksha.dto;

import java.util.ArrayList;
import java.util.List;

public class StandardDTO {
	
	private int id;
	private String name;
	private String code;
	
	private List<SectionDTO> sections = new ArrayList<>(); 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
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
	public List<SectionDTO> getSections() {
		return sections;
	}
	public void setSections(List<SectionDTO> sections) {
		this.sections = sections;
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
		StandardDTO other = (StandardDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StandardDTO [id=" + id + "]";
	}
	
	

}
