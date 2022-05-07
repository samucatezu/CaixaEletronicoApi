package com.example.caixaeletronicoapi.service;


import com.example.caixaeletronicoapi.model.Cedula;
import com.example.caixaeletronicoapi.model.dto.CedulaDTO;
import com.example.caixaeletronicoapi.tipo.TipoNota;
import com.example.caixaeletronicoapi.error.ValorIndisponivelException;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class SaqueServiceTest {

    SaqueService service;
    DispenserService dispenserService;

    @Before
    public void setup() {
        dispenserService = mock(DispenserService.class);
        service = new SaqueService(dispenserService);
    }



    @Test
    public void deveRetornarUmaNotaDeDezUmaDeVinteUmaDeCinquentaEDuasDeCemComSucesso() {
        List<CedulaDTO> cedulaDTO = new ArrayList<>();
        cedulaDTO.add(buildNotaDTO(2, TipoNota.NOTAS_100));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_50));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_20));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_10));
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_100, 2);
        mockCedula(TipoNota.NOTAS_50, 2);
        mockCedula(TipoNota.NOTAS_20, 2);
        mockCedula(TipoNota.NOTAS_10, 2);

        try {
            List<CedulaDTO> retorno = service.sacarCedulas(280);
            assertArrayEquals(cedulaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    @Test
    public void deveRetornarUmaNotaDeDezEUmaDeCinquentaComSucesso() {
        List<CedulaDTO> cedulaDTO = new ArrayList<>();
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_100));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_10));
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_100, 2);
        mockCedula(TipoNota.NOTAS_10, 2);

        try {
            List<CedulaDTO> retorno = service.sacarCedulas(110);
            assertArrayEquals(cedulaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    @Test
    public void deveRetornarDuasDeVinteEUmaDeCinquentaComSucesso() {
        List<CedulaDTO> cedulaDTO = new ArrayList<>();
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_50));
        cedulaDTO.add(buildNotaDTO(2, TipoNota.NOTAS_20));
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_50, 2);
        mockCedula(TipoNota.NOTAS_20, 2);

        try {
            List<CedulaDTO> retorno = service.sacarCedulas(90);
            assertArrayEquals(cedulaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    @Test
    public void deveRetornarUmaDeVinteEUmaDeDezComSucesso() {
        List<CedulaDTO> cedulaDTO = new ArrayList<>();
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_20));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_10));
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_20, 2);
        mockCedula(TipoNota.NOTAS_10, 2);

        try {
            List<CedulaDTO> retorno = service.sacarCedulas(30);
            assertArrayEquals(cedulaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    @Test
    public void deveRetornarUmaDeCinquentaUmaDeVinteEUmaDeDezComSucesso() {
        List<CedulaDTO> cedulaDTO = new ArrayList<>();
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_50));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_20));
        cedulaDTO.add(buildNotaDTO(1, TipoNota.NOTAS_10));
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_50, 2);
        mockCedula(TipoNota.NOTAS_20, 2);
        mockCedula(TipoNota.NOTAS_10, 2);

        try {
            List<CedulaDTO> retorno = service.sacarCedulas(80);
            assertArrayEquals(cedulaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    @Test
    public void deveGerarExceptionQuandoEncontrarValorMenorQueDez() {
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_100, 2);
        mockCedula(TipoNota.NOTAS_50, 2);
        mockCedula(TipoNota.NOTAS_20, 2);
        mockCedula(TipoNota.NOTAS_10, 2);

        try {
            service.sacarCedulas(285);
            fail("Deve retornar ValorIndisponivelException!");
        } catch (ValorIndisponivelException e) {
            assertEquals("Não é permitido Cedulas menor que 10!", e.getMessage());
        } catch (Exception e) {
            fail("Deve retornar ValorIndisponivelException!");
        }
    }

    @Test
    public void deveRetornarDuasDeCinquentaENoveDeDezQuandoDemiasNotasZeradasComSucesso() {
        List<CedulaDTO> cedulaDTO = new ArrayList<>();
        cedulaDTO.add(buildNotaDTO(2, TipoNota.NOTAS_50));
        cedulaDTO.add(buildNotaDTO(9, TipoNota.NOTAS_10));
        when(dispenserService.buscarNotasEmEstoque())
                .thenReturn(buildCedulasDispenser());

        mockCedula(TipoNota.NOTAS_100, 0);
        mockCedula(TipoNota.NOTAS_50, 2);
        mockCedula(TipoNota.NOTAS_20, 0);
        mockCedula(TipoNota.NOTAS_10, 9);
        try {
            List<CedulaDTO> retorno = service.sacarCedulas(190);
            assertArrayEquals(cedulaDTO.toArray(), retorno.toArray());
        } catch (Exception e) {
            fail("Deve executar com sucesso!");
        }
    }

    private CedulaDTO buildNotaDTO(Integer quantidade, TipoNota tipo) {
        CedulaDTO cedulaDTO = new CedulaDTO();
        cedulaDTO.setQuantidade(quantidade);
        cedulaDTO.setTipoNota(tipo);
        return cedulaDTO;
    }

    private List<Cedula> buildCedulasDispenser() {
        List<Cedula> notasDisponiveis = new ArrayList<>();
        notasDisponiveis.add(new Cedula(5, TipoNota.NOTAS_100));
        notasDisponiveis.add(new Cedula(7, TipoNota.NOTAS_50));
        notasDisponiveis.add(new Cedula(2, TipoNota.NOTAS_20));
        notasDisponiveis.add(new Cedula(5, TipoNota.NOTAS_10));
        return notasDisponiveis;
    }

    private void mockCedula(TipoNota tipoNota, Integer quantidade) {
        when(dispenserService.buscarCedulaDoTipo(tipoNota))
                .thenReturn(java.util.Optional.of(new Cedula(quantidade, tipoNota)));
    }
}
