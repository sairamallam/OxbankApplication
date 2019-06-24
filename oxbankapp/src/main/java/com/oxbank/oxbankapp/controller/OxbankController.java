package com.oxbank.oxbankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oxbank.oxbankapp.dto.ResponseDto;
import com.oxbank.oxbankapp.exception.OXBankingException;
import com.oxbank.oxbankapp.model.Loan;
import com.oxbank.oxbankapp.service.IOxbankService;


@RestController
@RequestMapping("/oxbank")
public class OxbankController {

	@Autowired
	private IOxbankService oxbankService;
	@PostMapping("/createloan")
	public ResponseEntity<ResponseDto>  createLoan(@RequestBody Loan loan){
		return new ResponseEntity<ResponseDto>(oxbankService.createLoan(loan),HttpStatus.CREATED);
		
	}
	@PutMapping("/approve")
	public ResponseEntity<ResponseDto> approveLoan(@RequestParam Long loanId,@RequestParam Long employeeId) throws OXBankingException{
		return new ResponseEntity<ResponseDto>(oxbankService.approveLoan(loanId,employeeId),HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteLoan(@RequestParam Long loanId,@RequestParam Long employeeId){
		return new ResponseEntity<ResponseDto>(oxbankService.deleteLoan(loanId,employeeId),HttpStatus.OK);
	}

}
