package com.oxbank.oxbankapp.testController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxbank.oxbankapp.controller.OxbankController;
import com.oxbank.oxbankapp.model.Bankofficer;
import com.oxbank.oxbankapp.model.Customer;
import com.oxbank.oxbankapp.model.Loan;
import com.oxbank.oxbankapp.service.OxbankServiceImpl;

@RunWith(MockitoJUnitRunner.class)
//@WebAppConfiguration
public class TestbankofficerController {

	@InjectMocks
	private OxbankController oxbankController;

	private MockMvc mockmvc;

	@Mock
	private OxbankServiceImpl OxbankServiceImpl;

	Loan loan;
	Customer customer;
	Bankofficer bankOfficer;

	@Before
	public void setUp() throws Exception {

		mockmvc = MockMvcBuilders.standaloneSetup(oxbankController).build();

		bankOfficer = new Bankofficer();
		bankOfficer.setEmployeeId(1L);
		bankOfficer.setLevel("BankOfficer");
		bankOfficer.setName("sai");

		customer = new Customer();
		customer.setCreditSccore(1000L);
		customer.setCustomerId(1L);
		customer.setDob(LocalDate.now());
		customer.setExpense(100L);
		customer.setGender("male");
		customer.setMaritalStatus("single");
		customer.setMobileNumber(99999999L);
		customer.setName("sarath");
		customer.setSalary(1000000L);
		customer.setWorkExperience(2f);

		loan = new Loan();
		loan.setAccountNumber(2378578L);
		loan.setBankOfficer(bankOfficer);
		loan.setLoanAmount(7867856D);
		loan.setLoanId(1L);

	}

	@Test
	public void testCreateLoan() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.post("/oxbank/createloan").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(loan))).andExpect(status().is(201));

	}

	@Test
	public void testApproveLoan() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.put("/oxbank/approve?loanId=1&employeeId=101"))
				.andExpect(status().is(200));

	}

	@Test
	public void testdeleteLoan() throws Exception {

		mockmvc.perform(MockMvcRequestBuilders.delete("/oxbank/delete?loanId=1&employeeId=101")).andExpect(status().is(200));

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}