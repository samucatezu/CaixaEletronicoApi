package com.example.caixaeletronicoapi.controller;


import com.example.caixaeletronicoapi.model.ATM;
import com.example.caixaeletronicoapi.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atm")
public class ATMController {
	
	@Autowired
	ATMService atmService;

	
	@GetMapping("/seeCash")
	public ATM seeCash() {
		return atmService.seeCash();
	}
	
	@PutMapping("/withdrawMoney/{value}")
	@ResponseBody
	public String withdrawMoney(@PathVariable Integer value) {
		return atmService.withdrawMoney(value);
	}
	
	@PutMapping("/addCash/{bankNote}/{amount}")
	public ATM addCash(@PathVariable("bankNote") Integer bankNote, @PathVariable("amount") Integer amount) {
		
		ATM atm = atmService.addCash(bankNote, amount);
		if(atm.equals(null))
			return null;
		return atm;
		//"This ATM dosn't work with this type of cedule";
	}
}
