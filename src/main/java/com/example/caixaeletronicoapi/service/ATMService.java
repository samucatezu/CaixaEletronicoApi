package com.example.caixaeletronicoapi.service;


import com.example.caixaeletronicoapi.model.ATM;
import com.example.caixaeletronicoapi.repository.ATMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ATMService {

	@Autowired
	ATMRepository atmRepository;

	private static int WITHDRAW_MIN_VALUE = 0;
	private static int WITHDRAW_MAX_VALUE = 10000;

	private ATM getATM() {
		return atmRepository.findById(0);
	}

	public ATM seeCash() {
		return this.getATM();
	}

	public ATM addCash(int bankNote, int newAmount) {

		ATM atm = this.getATM();
		int oldAmount = 0;	

		switch (bankNote) {
		case 50: oldAmount = atm.getAmount50();	
		atm.setAmount50(oldAmount + newAmount);
		break;
		case 20: oldAmount = atm.getAmount20();	
		atm.setAmount20(oldAmount + newAmount);
		break;
		case 10: oldAmount = atm.getAmount10();	
		atm.setAmount10(oldAmount + newAmount);
		break;
		case 5: oldAmount = atm.getAmount5();
		atm.setAmount5(oldAmount + newAmount);
		break;
		case 2: oldAmount = atm.getAmount2();
		atm.setAmount2(oldAmount + newAmount);
		break;

		default: return null;

		}

		return atmRepository.save(atm);
	}

	public String withdrawMoney(int value) {

		if(value < WITHDRAW_MIN_VALUE || value > WITHDRAW_MAX_VALUE)
			return "Value is not accepted";

		ATM atm = this.getATM();
		int count50 = 0, count20 = 0, count10 = 0, count5 = 0, count2 = 0;

		if(!isValidNumber(value))
			return "Invalid Number";
		if(!atmHasMoney(value))
			return "ATM does not have enough money";
		else {

			while(value > 0) {

				if(atm.getAmount50() > 0 && value - 50 >= 0) {
					count50 ++;
					value =  value - 50;
					atm.setAmount50(atm.getAmount50() - 1);
				}
				else if(atm.getAmount20() > 0 && value - 20 >= 0) {
					count20 ++;
					value =  value - 20;
					atm.setAmount20(atm.getAmount20() - 1);
				}

				else if(atm.getAmount10() > 0 && value - 10 >= 0) {
					count10 ++;
					value =  value - 10;
					atm.setAmount10(atm.getAmount10() - 1);
				}

				else if(value - 5 >= 0 && value % 5 == 0) {
					if(atm.getAmount5() > 0) {
						count5 ++;				
						value =  value - 5;
					}else {
						return "ATM is missing banknotes of 2. Sorry for incovenient.";
					}
				}else if(value - 2 >= 0 && value % 2 == 0) {
					if(atm.getAmount2() > 0) {
						count2 ++;				
						value =  value - 2;
					}else {
						return "ATM is missing banknotes of 2. Sorry for incovenient.";
					}	
				}
			}

			atmRepository.save(atm);
			String amountCedules = "Cash ATM: \nAmount 50: " + count50 + "\nAmount 20: " + count20
					+ "\nAmount 10: " + count10 + "\nAmount 5: " + count5 + "\nAmount 2: " + count2;

			return amountCedules;
		}
	}

	private boolean isValidNumber(int value) {

		ATM atm = this.getATM();

		while(value > 0 && value != 1) {

			if(value % 5 == 0)
				value = value - 5 ;
			else if (value % 2 == 0)
				value = value - 2;
			else
				return false;
		}

		return true;
	}

	private boolean atmHasMoney(int value) {

		ATM atm = this.getATM();
		int sumATM = (atm.getAmount50() * 50) + (atm.getAmount20() * 20) + (atm.getAmount10() * 10) + (atm.getAmount5() * 5) + (atm.getAmount2() * 2);

		if(sumATM >= value) {
			return true;
		}else
			return false;
	}

}
