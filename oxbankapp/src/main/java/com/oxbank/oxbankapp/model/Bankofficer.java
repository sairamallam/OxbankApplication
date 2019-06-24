package com.oxbank.oxbankapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bankofficer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;

	@JsonIgnore
	private String name;
	@JsonIgnore
	private String level;
	
	public Bankofficer() {
		// TODO Auto-generated constructor stub
	}


	public Long getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	
	
	

}
