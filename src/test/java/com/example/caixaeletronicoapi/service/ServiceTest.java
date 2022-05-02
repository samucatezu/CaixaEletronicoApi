package com.example.caixaeletronicoapi.service;


import com.example.caixaeletronicoapi.model.BankData;
import com.example.caixaeletronicoapi.repository.BankDataRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceTest {

    @Autowired
    BankDataRepository bankDataRepository;

    public BankData createBankDataDefaultMock() {
        BankData bankData = BankData.builder()
                .agency("1235").accountNumber(new Long(System.currentTimeMillis()).toString()).balance(new BigDecimal(20)).build();
        bankData.setCreatedAt(new Date());
        bankData.setUpdatedAt(new Date());
        return bankDataRepository.save(bankData);
    }


}
