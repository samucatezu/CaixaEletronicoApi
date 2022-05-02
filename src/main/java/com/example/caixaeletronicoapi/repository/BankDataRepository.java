package com.example.caixaeletronicoapi.repository;

import com.example.caixaeletronicoapi.model.BankData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankDataRepository extends JpaRepository<BankData, Long> {

    Optional<BankData> findByAgencyAndAccountNumber(String agency, String accountNumber);

}
