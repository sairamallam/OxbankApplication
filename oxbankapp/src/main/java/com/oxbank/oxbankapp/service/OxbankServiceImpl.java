package com.oxbank.oxbankapp.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oxbank.oxbankapp.dto.ResponseDto;
import com.oxbank.oxbankapp.exception.OXBankingException;
import com.oxbank.oxbankapp.model.Bankofficer;
import com.oxbank.oxbankapp.model.Customer;
import com.oxbank.oxbankapp.model.Loan;
import com.oxbank.oxbankapp.repository.IBankOfficer;
import com.oxbank.oxbankapp.repository.ICustomerRepository;
import com.oxbank.oxbankapp.repository.ILoanRepository;

@Service
public class OxbankServiceImpl implements IOxbankService {
	@Autowired
	private ILoanRepository loanRepository;
	@Autowired
	private ICustomerRepository customerRepository;
	@Autowired
	private IBankOfficer bankOfficer;

	@Override
	public ResponseDto createLoan(Loan loan) {

		loanRepository.save(loan);

		return new ResponseDto("Loan created for customer sucessfully");
	}

	@Override
	public ResponseDto approveLoan(Long loanId, Long employeeId) throws OXBankingException {
		Optional<Loan> loans = loanRepository.findById(loanId);
		Loan loan=loans.get();
		Long appliedCustomerId = loan.getCustomer().getCustomerId();
		Customer appliedCustomer = customerRepository.getOne(appliedCustomerId);
		Optional<Bankofficer> officer = bankOfficer.findById(employeeId);
		if (officer.get().getLevel().equalsIgnoreCase("Officer")) {
			if (appliedCustomer.getCreditSccore() > 900) {
				if (appliedCustomer.getWorkExperience() > 2) {
					int age = Period.between(appliedCustomer.getDob(), LocalDate.now()).getYears();
					if (age >= 24 && age <= 60) {
						loan.setLoanStatus("approved");
						loan.setBankOfficer(officer.get());
						loanRepository.save(loan);
					} else {
						throw new OXBankingException("age should be greater than 24");
					}
				} else
					throw new OXBankingException("work experience is less");
			} else {
				throw new OXBankingException("Credit score is less");
			}
		} else {
			throw new OXBankingException("Only officer can approve the loan");
		}
		return new ResponseDto("Loan approved for customer sucessfully");

	}

	@Override
	public ResponseDto deleteLoan(Long loanId, Long employeeId)  {
		Optional<Loan> loan = loanRepository.findById(loanId);
	    Optional<Bankofficer> bankofficer=bankOfficer.findById(employeeId);
		if (bankofficer.get().getLevel().equalsIgnoreCase("Manager")) {
			if ((loan.isPresent()) && (loan.get().getLoanStatus()==null)) {
				loanRepository.delete(loan.get());
			} else {
				throw new OXBankingException("Record not found/Loan is already approved");
			}
		}
	    else {
	    	throw new OXBankingException("Only manager can delete the loan!!");
	    }

		return new ResponseDto("Loan deleted sucessfully");
	}

}
