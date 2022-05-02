package com.example.caixaeletronicoapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountOperationsController {

    @PatchMapping("/withdraw/{value}")
    public ResponseEntity withdraw(@RequestParam Double value) {
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/deposit/{value}")
    public ResponseEntity deposit(@RequestParam Double value) {
        return ResponseEntity.ok().build();

    }

}
