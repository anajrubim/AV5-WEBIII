package com.autobots.msvenda.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import lombok.Data;

@Data
@Component
public class ProvedorJwt {

    @Value("${jwt.secret}")
    private String assinatura;

    @Value("${jwt.expiration}")
    private Long duracao;

    public boolean validarJwt(String jwt) {
        AnalisadorJwt analisador = new AnalisadorJwt(assinatura, jwt);
        ValidadorJwt validador = new ValidadorJwt();
        return validador.validar(analisador.obterReivindicacoes());
    }

    public Claims obterReivindicacoes(String jwt) {
        AnalisadorJwt analisador = new AnalisadorJwt(assinatura, jwt);
        return analisador.obterReivindicacoes();
    }

    public String obterNomeUsuario(String jwt) {
        AnalisadorJwt analisador = new AnalisadorJwt(assinatura, jwt);
        Claims reivindicacoes = analisador.obterReivindicacoes();
        return analisador.obterNomeUsuario(reivindicacoes);
    }
}
