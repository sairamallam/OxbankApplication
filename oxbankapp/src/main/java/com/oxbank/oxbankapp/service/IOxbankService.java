package com.oxbank.oxbankapp.service;

import com.oxbank.oxbankapp.dto.ResponseDto;
import com.oxbank.oxbankapp.exception.OXBankingException;
import com.oxbank.oxbankapp.model.Loan;



public interface IOxbankService {
	public ResponseDto createLoan(Loan loan);

	public ResponseDto deleteLoan(Long accountNumber,Long employeeId);

	public ResponseDto approveLoan(Long loanId, Long employeeId)throws OXBankingException;

}
