package com.oxbank.oxbankapp.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.oxbank.oxbankapp.dto.ResponseDto;
import com.oxbank.oxbankapp.exception.OXBankingException;
import com.oxbank.oxbankapp.model.Bankofficer;
import com.oxbank.oxbankapp.model.Customer;
import com.oxbank.oxbankapp.model.Loan;
import com.oxbank.oxbankapp.repository.IBankOfficer;
import com.oxbank.oxbankapp.repository.ICustomerRepository;
import com.oxbank.oxbankapp.repository.ILoanRepository;
import com.oxbank.oxbankapp.service.OxbankServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OxbankServiceImplTest {

	@InjectMocks
	OxbankServiceImpl oxbankServiceImpl;

	@Mock
	IBankOfficer iBankOfficer;

	@Mock
	ICustomerRepository iCustomerRepository;

	@Mock
	ILoanRepository iLoanRepository;

	@Test
	public void createLoanImplTest() {
		Loan loan = getLoan();
		Mockito.when(iLoanRepository.save(loan)).thenReturn(loan);
		ResponseDto actualResponse = oxbankServiceImpl.createLoan(loan);
		Assert.assertEquals("Loan created for customer sucessfully", actualResponse.getMessage());

	}

	@Test
	public void approveLoanTest() {
		Loan loan = getLoan();
		Bankofficer bankOfficer = getBankOfficerDetails();
		Customer customer = getCustomer();

		Mockito.when(iLoanRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(loan));
		Mockito.when(iCustomerRepository.getOne(Mockito.anyLong())).thenReturn(customer);
		Mockito.when(iBankOfficer.findById(Mockito.anyLong())).thenReturn(Optional.of(bankOfficer));
		// Mockito.when(iLoanRepository.save(loan)).thenReturn(loan);
		ResponseDto actualResponse = oxbankServiceImpl.approveLoan(loan.getLoanId(), bankOfficer.getEmployeeId());
		Assert.assertEquals("Loan approved for customer sucessfully", actualResponse.getMessage());

	}

	@Test(expected = OXBankingException.class)
	public void approveLoan_1Test() {
		Loan loan = getLoan();
		Bankofficer bankOfficer = getBankOfficerDetails();
		Customer customer = getCustomer();

		customer.setCreditSccore(600L);
		Mockito.when(iLoanRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(loan));
		Mockito.when(iCustomerRepository.getOne(Mockito.anyLong())).thenReturn(customer);
		Mockito.when(iBankOfficer.findById(Mockito.anyLong())).thenReturn(Optional.of(bankOfficer));
		// Mockito.when(iLoanRepository.save(loan)).thenReturn(loan);
		oxbankServiceImpl.approveLoan(loan.getLoanId(), bankOfficer.getEmployeeId());

	}
	@Test(expected = OXBankingException.class)
	public void approveLoan_2Test() {
		Loan loan = getLoan();
		Bankofficer bankOfficer = getBankOfficerDetails();
		Customer customer = getCustomer();

		customer.setWorkExperience(1L);
		Mockito.when(iLoanRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(loan));
		Mockito.when(iCustomerRepository.getOne(Mockito.anyLong())).thenReturn(customer);
		Mockito.when(iBankOfficer.findById(Mockito.anyLong())).thenReturn(Optional.of(bankOfficer));
		// Mockito.when(iLoanRepository.save(loan)).thenReturn(loan);
		oxbankServiceImpl.approveLoan(loan.getLoanId(), bankOfficer.getEmployeeId());

	}

	@Test(expected = OXBankingException.class)
	public void approveLoan_3Test() {
		Loan loan = getLoan();
		Bankofficer bankOfficer = getBankOfficerDetails();
		Customer customer = getCustomer();
		
		customer.setWorkExperience(1L);
		bankOfficer.setLevel("Manager");
		Mockito.when(iLoanRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(loan));
		Mockito.when(iCustomerRepository.getOne(Mockito.anyLong())).thenReturn(customer);
		Mockito.when(iBankOfficer.findById(Mockito.anyLong())).thenReturn(Optional.of(bankOfficer));
		// Mockito.when(iLoanRepository.save(loan)).thenReturn(loan);
		oxbankServiceImpl.approveLoan(loan.getLoanId(), bankOfficer.getEmployeeId());

	}

	@Test
	public void deleteLoanTest() {
		Loan loan = getLoan();
		Bankofficer bankOfficer = getBankOfficerDetails();

		bankOfficer.setLevel("Manager");
		Mockito.when(iLoanRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(loan));
		Mockito.when(iBankOfficer.findById(Mockito.anyLong())).thenReturn(Optional.of(bankOfficer));
		ResponseDto actualResponse = oxbankServiceImpl.deleteLoan(loan.getLoanId(), bankOfficer.getEmployeeId());
		Assert.assertEquals("Loan deleted sucessfully", actualResponse.getMessage());
	}

	@Test(expected = OXBankingException.class)
	public void deleteLoan_1Test() {
		Loan loan = getLoan();
		Bankofficer bankOfficer = getBankOfficerDetails();
		bankOfficer.setLevel("Officer");
		Mockito.when(iBankOfficer.findById(Mockito.anyLong())).thenReturn(Optional.of(bankOfficer));
		oxbankServiceImpl.deleteLoan(loan.getLoanId(), bankOfficer.getEmployeeId());
	}

	@Test(expected = OXBankingException.class)
	public void deleteLoan_2Test() {
		Loan loan = getLoan();
		Bankofficer bankOfficer = getBankOfficerDetails();
		loan.setLoanStatus("null");
		bankOfficer.setLevel("Officer");
		Mockito.when(iBankOfficer.findById(Mockito.anyLong())).thenReturn(Optional.of(bankOfficer));
		oxbankServiceImpl.deleteLoan(loan.getLoanId(), bankOfficer.getEmployeeId());
	}

	/*
	 * @Test public void deleteLoan_2Test() { // Loan loan = getLoan();
	 * loan.setLoanStatus("Approved"); //Bankofficer bankofficer =
	 * getBankOfficerDetails();
	 * Mockito.when(iLoanRepository.findById(Mockito.anyLong())).thenReturn(Optional
	 * .of(loan));
	 * Mockito.when(iBankOfficer.findById(Mockito.anyLong())).thenReturn(Optional.of
	 * (bankOfficer)); oxbankServiceImpl.deleteLoan(loan.getLoanId(),
	 * bankOfficer.getEmployeeId()); //
	 * Assert.assertEquals("Only manager can delete the loan!!", //
	 * actualResponse.getMessage()); }
	 */

	public Loan getLoan() {
		Loan loan = new Loan();
		loan.setLoanId(1L);
		loan.setAccountNumber(1111L);
		Customer customer = getCustomer();
		loan.setCustomer(customer);
		loan.setLoanAmount(20000);
		return loan;
	}

	public Customer getCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCreditSccore(901L);
		LocalDate localDate = localDob("1991/03/23");
		customer.setDob(localDate);
		customer.setExpense(2000);
		customer.setGender("Male");
		customer.setMaritalStatus("Single");
		customer.setMobileNumber(6767676767L);
		customer.setName("Giri");
		customer.setSalary(50000L);
		customer.setWorkExperience(3);
		return customer;
	}

	public Bankofficer getBankOfficerDetails() {
		Bankofficer bankOfficer = new Bankofficer();
		bankOfficer.setEmployeeId(1L);
		bankOfficer.setLevel("Officer");
		bankOfficer.setName("Priys");
		return bankOfficer;
	}

	public LocalDate localDob(String dob) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String dateString = dob;

		LocalDate localDateObj = LocalDate.parse(dateString, dateTimeFormatter); // String to LocalDate

		// String dateStr = localDateObj.format(dateTimeFormatter); //LocalDate to
		// String
		return localDateObj;
	}

}
