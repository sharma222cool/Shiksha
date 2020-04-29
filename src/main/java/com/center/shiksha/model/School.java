package com.center.shiksha.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.center.shiksha.model.address.Address;

@Entity
public class School {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String code;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	@ManyToMany
	@JoinTable(name = "school_standard", 
			  joinColumns = {@JoinColumn(name = "school_id", referencedColumnName = "id")}, 
			  inverseJoinColumns = {@JoinColumn(name = "standard_id", referencedColumnName = "id")})
	private List<Standard> standards;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
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
	public List<Standard> getStandards() {
		return standards;
	}
	public void setStandards(List<Standard> standards) {
		this.standards = standards;
	}	
	
	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + ", code=" + code + ", address=" + address + "]";
	}
	
	
}
