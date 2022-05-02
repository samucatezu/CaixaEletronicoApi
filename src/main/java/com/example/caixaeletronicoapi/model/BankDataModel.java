package com.example.caixaeletronicoapi.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;

@Entity(name="BANK_DATA")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints=@UniqueConstraint(columnNames = { "agency","accountNumber" }))
public class BankDataModel extends AbstractModel{

    private static final long serialVersionUID = 61971462715115176L;

    @Column(nullable=false)
    private String agency;

    @Column(nullable=false)
    private String accountNumber;
    @Column(scale=2)
    private BigDecimal balance;



    public boolean canWithdraw(BigDecimal value ) {
        return (BigDecimal.ZERO.compareTo(balance.subtract(value)) <= 0) ? true : false;
    }

    public void withdraw(BigDecimal value ) {
        if(canWithdraw(value)) {
            this.balance = balance.subtract(value);
            return;
        }
        throw new BusinessException("Not enough founds!");
    }

    public void deposit(BigDecimal value ) {
        this.balance = balance.add(value);
    }
}
