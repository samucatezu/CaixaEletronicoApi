package com.example.caixaeletronicoapi.model.dto;



import com.example.caixaeletronicoapi.tipo.TipoNota;
import lombok.Data;

@Data
public class CedulaDTO {
    private TipoNota tipoNota;
    private Integer quantidade;
}
