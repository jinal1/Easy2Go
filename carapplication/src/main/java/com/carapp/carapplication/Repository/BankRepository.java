package com.carapp.carapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carapp.carapplication.model.Bank;

public interface BankRepository  extends JpaRepository<Bank,Long> {

}
