package com.example.caixaeletronicoapi.controller;


import com.example.caixaeletronicoapi.error.NumeroDeNotasIndisponivelException;
import com.example.caixaeletronicoapi.error.ValorIndisponivelException;
import com.example.caixaeletronicoapi.service.SaqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saques")
public class SaqueController {

    private final SaqueService saqueService;

    public SaqueController(SaqueService saqueService) {
        this.saqueService = saqueService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{valor}")
    public ResponseEntity<?> retornaQuantidadeDeNotas(@PathVariable("valor") Integer valor) throws ValorIndisponivelException, NumeroDeNotasIndisponivelException {
        return new ResponseEntity<>(saqueService.sacarCedulas(valor), HttpStatus.OK);
    }

}
