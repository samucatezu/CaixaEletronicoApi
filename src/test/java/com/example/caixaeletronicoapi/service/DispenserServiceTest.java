package com.example.caixaeletronicoapi.service;


import com.example.caixaeletronicoapi.error.CedulaIndisponivelException;
import com.example.caixaeletronicoapi.error.NumeroDeNotasIndisponivelException;
import com.example.caixaeletronicoapi.model.Cedula;
import com.example.caixaeletronicoapi.repository.DispenserRepository;
import com.example.caixaeletronicoapi.tipo.TipoNota;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DispenserServiceTest {

    private DispenserService service;
    DispenserRepository dispenserRepository;


    @Before

    public void setup() {
        dispenserRepository = mock(DispenserRepository.class);
        when(dispenserRepository.buscarNotasDispenser())
                .thenReturn(buildCedulasDispenser());
        service = new DispenserService(dispenserRepository);
    }

    @Test
    public void deveRetornarCedulasDoTipo100ComSucesso() {

        Optional<Cedula> retorno = service.buscarCedulaDoTipo(TipoNota.NOTAS_100);

        assertEquals(Optional.of(5), Optional.ofNullable(retorno.get().getQuantidadeDisponivel()));

    }

    @Test
    public void deveRetornarCedulaVaziaQuandoNaoEncontrar() {

        Optional<Cedula> retorno = service.buscarCedulaDoTipo(TipoNota.NOTAS_10);

        assertFalse(retorno.isPresent());

    }

    @Test
    public void deveAtualizarRetiradaDeCedulasComSucesso() {

        try {
            service.atualizarRetiraDeCedulas(TipoNota.NOTAS_100, 3);
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    @Test
    public void deveLnacarExceptionAoAtualizarRetiradaDeCedulasQuandoRetiradaMaiorQueDisponivel() {

        try {
            service.atualizarRetiraDeCedulas(TipoNota.NOTAS_100, 9);
        } catch (NumeroDeNotasIndisponivelException e) {
            assertEquals("Número de notas indisponível!", e.getMessage());
        } catch (Exception e) {
            fail("Deve lançar NumeroDeNotasIndisponivelException!");
        }
    }

    @Test
    public void deveLancarExceptionAoAtualizarRetiradaDeCedulasQuandoCedulaNaoEncontrada() {

        try {
            service.atualizarRetiraDeCedulas(TipoNota.NOTAS_10, 9);
        } catch (CedulaIndisponivelException e) {
            assertEquals("Cedula não encontrada!", e.getMessage());
        } catch (Exception e) {
            fail("Deve lançar CedulaIndisponivelException!");
        }
    }

    @Test
    public void deveBuscarNotasDoDispenserComSucesso() {

        List<Cedula> retorno = service.buscarNotasEmEstoque();
        assertEquals(buildCedulasDispenser(), retorno);
    }

    private List<Cedula> buildCedulasDispenser() {
        List<Cedula> notasDisponiveis = new ArrayList<>();
        notasDisponiveis.add(new Cedula(5, TipoNota.NOTAS_100));
        notasDisponiveis.add(new Cedula(7, TipoNota.NOTAS_50));
        notasDisponiveis.add(new Cedula(2, TipoNota.NOTAS_20));

        return notasDisponiveis;
    }

}
