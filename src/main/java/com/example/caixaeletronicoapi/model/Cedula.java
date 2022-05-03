package com.example.caixaeletronicoapi.model;


import com.example.caixaeletronicoapi.error.NumeroDeNotasIndisponivelException;
import com.example.caixaeletronicoapi.tipo.TipoNota;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Cedula {
    private Integer quantidadeDisponivel;
    private TipoNota nota;

    public Cedula(Integer quantidadeDisponivel, TipoNota nota) {
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.nota = nota;
    }

    public void retirarEstoque(Integer quantidade) throws NumeroDeNotasIndisponivelException {
        if (quantidadeDisponivel < quantidade) {
            throw new NumeroDeNotasIndisponivelException("Número de notas indisponível!");
        }
        quantidadeDisponivel = quantidadeDisponivel - quantidade;
    }
}
