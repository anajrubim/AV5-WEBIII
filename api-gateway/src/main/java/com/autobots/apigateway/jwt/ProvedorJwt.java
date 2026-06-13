package com.autobots.apigateway.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProvedorJwt {

    @Value("${jwt.secret}")
    private String assinatura;

    public boolean validarJwt(String jwt) {
        AnalisadorJwt analisador = new AnalisadorJwt(assinatura, jwt);
        ValidadorJwt validador = new ValidadorJwt();
        return validador.validar(analisador.obterReivindicacoes());
    }
}
