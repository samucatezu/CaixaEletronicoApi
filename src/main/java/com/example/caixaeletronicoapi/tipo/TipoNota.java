package com.example.caixaeletronicoapi.tipo;

import lombok.Getter;

@Getter
public enum TipoNota {
    NOTAS_10(10),
    NOTAS_20(20),
    NOTAS_50(50),
    NOTAS_100(100);
    private Integer value;

    private TipoNota(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
