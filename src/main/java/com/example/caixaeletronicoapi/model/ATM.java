
package com.example.caixaeletronicoapi.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "atm")
public class ATM implements Serializable{
	
	private static final long serialversionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int amount50;
	private int amount20;
	private int amount10;
	private int amount5;
	private int amount2;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAmount50() {
		return amount50;
	}
	public void setAmount50(int amount50) {
		this.amount50 = amount50;
	}
	public int getAmount20() {
		return amount20;
	}
	public void setAmount20(int amount20) {
		this.amount20 = amount20;
	}
	public int getAmount10() {
		return amount10;
	}
	public void setAmount10(int amount10) {
		this.amount10 = amount10;
	}
	public int getAmount5() {
		return amount5;
	}
	public void setAmount5(int amount5) {
		this.amount5 = amount5;
	}
	public int getAmount2() {
		return amount2;
	}
	public void setAmount2(int amount2) {
		this.amount2 = amount2;
	}

	@Override
	public String toString() {
		return "ATM\n cedules of 50 : " + getAmount50() + "\n cedules of 20 :" + getAmount20() + "\n cedules of 10 :"
				+ getAmount10() + "\n cedules of 5" + getAmount5() + "\n cedules of 2:" + getAmount2();
	}
	
	
	
	

}
