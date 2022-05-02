package com.example.caixaeletronicoapi.repository;

import java.util.Optional;

import com.example.caixaeletronicoapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long>{


    @Query("SELECT U FROM users U  WHERE U.bankData.accountNumber=:account and U.bankData.agency=:agency")
    Optional<User> findByUsername(@Param("agency") String agency, @Param("account") String account);
}
