package com.autobots.msempresa.filtros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.autobots.msempresa.jwt.ProvedorJwt;

import io.jsonwebtoken.Claims;

@SuppressWarnings("unchecked")
public class Autorizador extends BasicAuthenticationFilter {

    private ProvedorJwt provedorJwt;

    public Autorizador(AuthenticationManager gerenciadorAutenticacao, ProvedorJwt provedorJwt) {
        super(gerenciadorAutenticacao);
        this.provedorJwt = provedorJwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String cabecalho = request.getHeader("Authorization");
        ValidadorCabecalho validador = new ValidadorCabecalho(cabecalho);
        if (validador.validar()) {
            AnalisadorCabecalho analisador = new AnalisadorCabecalho(cabecalho);
            String jwt = analisador.obterJwt();
            if (provedorJwt.validarJwt(jwt)) {
                String nomeUsuario = provedorJwt.obterNomeUsuario(jwt);
                Claims claims = provedorJwt.obterReivindicacoes(jwt);
                List<GrantedAuthority> autoridades = new ArrayList<>();
                Object perfis = claims.get("perfis");
                if (perfis instanceof List<?>) {
                    for (Object perfil : (List<?>) perfis) {
                        autoridades.add(new SimpleGrantedAuthority(String.valueOf(perfil)));
                    }
                }
                UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(
                        nomeUsuario, null, autoridades);
                SecurityContextHolder.getContext().setAuthentication(autenticacao);
            }
        }
        chain.doFilter(request, response);
    }
}
