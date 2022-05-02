package com.example.caixaeletronicoapi;

public class BusinessException extends RuntimeException{

    public BusinessException() {
        super();
    }
    public BusinessException(String string) {
        super(string);
    }


}