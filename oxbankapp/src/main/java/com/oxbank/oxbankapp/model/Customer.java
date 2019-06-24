package com.oxbank.oxbankapp.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	@JsonIgnore
	private String name;
	@JsonIgnore
	private Long mobileNumber;
	@JsonIgnore
	private LocalDate dob;
	@JsonIgnore
	private String maritalStatus;
	@JsonIgnore
	private String gender;
	@JsonIgnore
	private Long creditSccore;
	@JsonIgnore
	private double salary;
	@JsonIgnore
	private double expense;
	@JsonIgnore
	private float workExperience;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Loan laon;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getCreditSccore() {
		return creditSccore;
	}

	public void setCreditSccore(Long creditSccore) {
		this.creditSccore = creditSccore;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public float getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(float workExperience) {
		this.workExperience = workExperience;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", mobileNumber=" + mobileNumber + ", dob="
				+ dob + ", maritalStatus=" + maritalStatus + ", gender=" + gender + ", creditSccore=" + creditSccore
				+ ", salary=" + salary + ", expense=" + expense + ", workExperience=" + workExperience + "]";
	}

}
