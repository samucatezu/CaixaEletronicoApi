package com.example.caixaeletronicoapi.controller;


import javax.validation.Valid;

import com.example.caixaeletronicoapi.controller.dto.AuthForm;
import com.example.caixaeletronicoapi.controller.dto.TokenDto;
import com.example.caixaeletronicoapi.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity <TokenDto>autenticar(@RequestBody @Valid AuthForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        Authentication authentication = authManager.authenticate(dadosLogin);
        return ResponseEntity.ok(new TokenDto(tokenService.generateToken(authentication), "Bearer"));
    }

}
