package com.oxbank.oxbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oxbank.oxbankapp.model.Customer;
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}
