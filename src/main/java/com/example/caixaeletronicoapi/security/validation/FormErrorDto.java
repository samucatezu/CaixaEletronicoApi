package com.example.caixaeletronicoapi.security.validation;

public class FormErrorDto {
    private String field;
    private String erro;

    public FormErrorDto(String field, String erro) {
        super();
        this.field = field;
        this.erro = erro;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public String getErro() {
        return erro;
    }
    public void setErro(String erro) {
        this.erro = erro;
    }


}

