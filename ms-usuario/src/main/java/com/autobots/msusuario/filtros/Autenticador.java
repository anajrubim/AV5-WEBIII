package com.autobots.msusuario.filtros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.autobots.msusuario.dto.CredencialDto;
import com.autobots.msusuario.jwt.ProvedorJwt;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Autenticador extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager gerenciadorAutenticacao;
    private ProvedorJwt provedorJwt;

    public Autenticador(AuthenticationManager gerenciadorAutenticacao, ProvedorJwt provedorJwt) {
        this.gerenciadorAutenticacao = gerenciadorAutenticacao;
        this.provedorJwt = provedorJwt;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        CredencialDto credencial;
        try {
            credencial = new ObjectMapper().readValue(request.getInputStream(), CredencialDto.class);
        } catch (IOException e) {
            credencial = new CredencialDto();
            credencial.setNomeUsuario("");
            credencial.setSenha("");
        }
        UsernamePasswordAuthenticationToken dadosAutenticacao = new UsernamePasswordAuthenticationToken(
                credencial.getNomeUsuario(), credencial.getSenha(), new ArrayList<>());
        return gerenciadorAutenticacao.authenticate(dadosAutenticacao);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication autenticacao) throws IOException, ServletException {
        UserDetails usuario = (UserDetails) autenticacao.getPrincipal();
        List<String> perfis = usuario.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String jwt = provedorJwt.proverJwt(usuario.getUsername(), perfis);
        response.addHeader("Authorization", "Bearer " + jwt);
    }
}
