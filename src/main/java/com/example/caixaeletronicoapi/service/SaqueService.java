package com.example.caixaeletronicoapi.service;


import com.example.caixaeletronicoapi.error.NumeroDeNotasIndisponivelException;
import com.example.caixaeletronicoapi.error.ValorIndisponivelException;
import com.example.caixaeletronicoapi.model.Cedula;
import com.example.caixaeletronicoapi.model.dto.CedulaDTO;
import com.example.caixaeletronicoapi.tipo.TipoNota;
import com.example.caixaeletronicoapi.error.CedulaIndisponivelException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaqueService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaqueService.class);
    private final DispenserService dispenser;

    public SaqueService(DispenserService dispenser) {
        this.dispenser = dispenser;
    }

    public List<CedulaDTO> sacarCedulas(Integer valor) throws ValorIndisponivelException, NumeroDeNotasIndisponivelException {

        List<CedulaDTO> cedulaDTOSList = buscarDistribuicaoDeCedulas(valor);
        atualizarDispenser(cedulaDTOSList);
        return cedulaDTOSList;
    }

    private List<CedulaDTO> buscarDistribuicaoDeCedulas(Integer valor) throws NumeroDeNotasIndisponivelException, ValorIndisponivelException {
        List<CedulaDTO> listaCedulas = new ArrayList<>();
        Integer valorRestante = valor;
        for (Cedula cedula : dispenser.buscarNotasEmEstoque()) {
            Optional<CedulaDTO> cedulaDTO = buscarQuantidadeDeCedulasDoTipo(cedula.getNota(), valorRestante);
            if (cedulaDTO.isPresent()) {
                listaCedulas.add(cedulaDTO.get());
                valorRestante = valorRestante - (cedula.getNota().getValue() * cedulaDTO.get().getQuantidade());
            }
        }
        if (valorRestante >= 10) {
            throw new NumeroDeNotasIndisponivelException("Valor solicitado indisponível!");
        } else if (valorRestante != 0) {
            throw new ValorIndisponivelException("Não é permitido Cedulas menor que 10!");
        }
        return listaCedulas;
    }

    private Optional<CedulaDTO> buscarQuantidadeDeCedulasDoTipo(TipoNota tipoNota, Integer valorRestante) {
        if (valorRestante >= tipoNota.getValue()) {
            Integer quantidade = valorRestante / tipoNota.getValue();
            Optional<Cedula> cedula = dispenser.buscarCedulaDoTipo(tipoNota);
            if (cedula.isPresent()) {
                if (quantidade > cedula.get().getQuantidadeDisponivel()) {
                    quantidade = cedula.get().getQuantidadeDisponivel();
                }
                if (quantidade > 0) {
                    return Optional.of(buildCedulaDTO(quantidade, tipoNota));
                }
            }
        }
        return Optional.empty();
    }

    private void atualizarDispenser(List<CedulaDTO> cedulaDTOList) {
        cedulaDTOList
                .stream()
                .forEach(cedulaDTO -> {
                    try {
                        dispenser.atualizarRetiraDeCedulas(cedulaDTO.getTipoNota(), cedulaDTO.getQuantidade());
                    } catch (CedulaIndisponivelException e) {
                        LOGGER.error(e.getMessage());
                    } catch (NumeroDeNotasIndisponivelException e) {
                        LOGGER.error(e.getMessage());
                    }
                });
    }

    private CedulaDTO buildCedulaDTO(Integer quantidade, TipoNota tipo) {
        CedulaDTO cedulaDTO = new CedulaDTO();
        cedulaDTO.setQuantidade(quantidade);
        cedulaDTO.setTipoNota(tipo);
        return cedulaDTO;
    }
}
