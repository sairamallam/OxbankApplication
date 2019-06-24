package com.oxbank.oxbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oxbank.oxbankapp.model.Bankofficer;

@Repository
public interface IBankOfficer extends JpaRepository<Bankofficer,Long> {

}
