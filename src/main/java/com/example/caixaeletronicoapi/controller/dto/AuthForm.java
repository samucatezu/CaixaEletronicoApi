package com.example.caixaeletronicoapi.controller.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;


public class AuthForm {
    @NotEmpty
    private String account;
    @NotEmpty
    private String agency;
    @NotEmpty
    private String password;


    public String getAccount() {
        return account;
    }


    public void setAccount(String account) {
        this.account = account;
    }


    public String getAgency() {
        return agency;
    }


    public void setAgency(String agency) {
        this.agency = agency;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(account+";"+agency, password);
    }
}
