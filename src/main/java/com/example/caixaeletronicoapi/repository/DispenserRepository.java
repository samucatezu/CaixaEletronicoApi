package com.example.caixaeletronicoapi.repository;

import com.example.caixaeletronicoapi.model.Cedula;
import com.example.caixaeletronicoapi.tipo.TipoNota;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DispenserRepository {
    private List<Cedula> notasDisponiveis;

    public DispenserRepository() {
        notasDisponiveis = new ArrayList<>();
        notasDisponiveis.add(new Cedula(5, TipoNota.NOTAS_100));
        notasDisponiveis.add(new Cedula(7, TipoNota.NOTAS_50));
        notasDisponiveis.add(new Cedula(2, TipoNota.NOTAS_20));
        notasDisponiveis.add(new Cedula(5, TipoNota.NOTAS_10));
    }

    public List<Cedula> buscarNotasDispenser() {
        return notasDisponiveis;
    }
}
