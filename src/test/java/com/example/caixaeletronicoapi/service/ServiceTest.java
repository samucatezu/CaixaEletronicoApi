package com.example.caixaeletronicoapi.service;


import com.example.caixaeletronicoapi.model.ATM;
import com.example.caixaeletronicoapi.repository.ATMRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    static ATMService atmService;

    @Autowired
    static ATMRepository atmRepository;

    static ATM atm;

    @BeforeAll
    void setup() {

        atm = new ATM();
        atm.setAmount2(5);
        atm.setAmount5(5);
        atm.setAmount10(5);
        atm.setAmount20(5);
        atm.setAmount50(5);
        atmRepository.save(atm);

        //The total amount of 'atm' is 435.
    }

    @Test
    public void seeCashTest() {


        //just to see if the method is working as expected
        assertTrue(atmService.seeCash().getAmount50() == atm.getAmount50());
        atm.setAmount50(0);

        atmRepository.save(atm);
        assertFalse(atmService.seeCash().getAmount50() == atm.getAmount50());
    }

    @Test
    public void addCashTest() {

        atmService.addCash(50, 5);
        assertTrue(atmService.seeCash().getAmount50() == 10);

        //ATM just accepts bank notes: 50,20,10,5,2
        assertNull(atmService.addCash(100, 1));
        assertNull(atmService.addCash(-10, 1));


    }

    @Test
    public void withdrawMoneyTest() {

        int value = 250;
        atmService.withdrawMoney(value);

        assertAll("Value Not accepted",
                () -> atmService.withdrawMoney(0),
                () -> atmService.withdrawMoney(100000));

        //now the ATM does not has 50 banknotes.
        assertTrue(atmService.seeCash().getAmount50() == 0);

        value = 185;
        atmService.withdrawMoney(value);

        //now the ATM does not has any banknotes.
        assertFalse(atmService.seeCash().getAmount2() == 1);

        //withdraw cash without money at ATM is impossible.
        assertEquals("ATM does not have enough money", atmService.withdrawMoney(100));

        //withdraw cash with negative amount is impossible too.
        assertEquals("Invalid Number", atmService.withdrawMoney(-20));

        atm.setAmount10(2);
        atmRepository.save(atm);

        //this must not be allowed because even the ATM having money enough, it has not the correct banknotes.
        assertEquals("ATM does not have enough money", atmService.withdrawMoney(12));
        assertEquals("ATM does not have enough money", atmService.withdrawMoney(15));
        assertEquals("Invalid Number", atmService.withdrawMoney(13));
    }


}
