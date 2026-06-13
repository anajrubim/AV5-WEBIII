package com.autobots.msvenda.cliente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.autobots.msvenda.dto.UsuarioDto;

@Component
public class UsuarioCliente {

    @Value("${ms.usuario.url}")
    private String urlBase;

    private final RestTemplate restTemplate = new RestTemplate();

    public UsuarioDto buscar(Long id, String jwt) {
        if (id == null) return null;
        try {
            HttpHeaders headers = new HttpHeaders();
            if (jwt != null) headers.set("Authorization", jwt);
            HttpEntity<Void> entidade = new HttpEntity<>(headers);
            return restTemplate.exchange(urlBase + "/usuario/" + id, HttpMethod.GET, entidade, UsuarioDto.class)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
