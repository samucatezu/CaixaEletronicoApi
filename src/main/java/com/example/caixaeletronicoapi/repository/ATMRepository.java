package com.example.caixaeletronicoapi.repository;


import com.example.caixaeletronicoapi.model.ATM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ATMRepository extends JpaRepository<ATM, Long>  {
	
	ATM findById(long id);
		
}