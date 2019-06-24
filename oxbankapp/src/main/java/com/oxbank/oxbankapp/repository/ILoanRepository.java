package com.oxbank.oxbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oxbank.oxbankapp.model.Loan;

@Repository
public interface ILoanRepository extends JpaRepository<Loan, Long> {

	@Query("select l from Loan l where l.loanId=:loanId and l.bankOfficer.employeeId=:employeeId")
	Loan findByloanIdAndManagerId(@Param(value = "loanId") Long loanId,@Param(value = "employeeId") Long employeeId);
	
}
