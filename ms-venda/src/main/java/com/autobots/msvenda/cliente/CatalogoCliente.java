package com.autobots.msvenda.cliente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.autobots.msvenda.dto.MercadoriaDto;
import com.autobots.msvenda.dto.ServicoDto;

@Component
public class CatalogoCliente {

    @Value("${ms.catalogo.url}")
    private String urlBase;

    private final RestTemplate restTemplate = new RestTemplate();

    public MercadoriaDto buscarMercadoria(Long id, String jwt) {
        if (id == null) return null;
        try {
            HttpHeaders headers = new HttpHeaders();
            if (jwt != null) headers.set("Authorization", jwt);
            HttpEntity<Void> entidade = new HttpEntity<>(headers);
            return restTemplate.exchange(urlBase + "/mercadoria/" + id, HttpMethod.GET, entidade, MercadoriaDto.class)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public ServicoDto buscarServico(Long id, String jwt) {
        if (id == null) return null;
        try {
            HttpHeaders headers = new HttpHeaders();
            if (jwt != null) headers.set("Authorization", jwt);
            HttpEntity<Void> entidade = new HttpEntity<>(headers);
            return restTemplate.exchange(urlBase + "/servico/" + id, HttpMethod.GET, entidade, ServicoDto.class)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
