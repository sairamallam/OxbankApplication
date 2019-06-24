package com.oxbank.oxbankapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long loanId;
	private Long accountNumber;
	@JsonIgnore
	private String loanStatus;
	private double loanAmount;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "approvingManagerId")
	private Bankofficer bankOfficer;

	
	@OneToOne
	private Customer customer;

	public Loan() {
		// TODO Auto-generated constructor stub
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public Bankofficer getBankOfficer() {
		return bankOfficer;
	}

	public void setBankOfficer(Bankofficer bankOfficer) {
		this.bankOfficer = bankOfficer;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
