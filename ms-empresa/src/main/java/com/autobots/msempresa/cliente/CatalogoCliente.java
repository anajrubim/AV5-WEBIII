package com.autobots.msempresa.cliente;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.autobots.msempresa.dto.MercadoriaDto;
import com.autobots.msempresa.dto.ServicoDto;
@Component
public class CatalogoCliente {

    @Value("${ms.catalogo.url}")
    private String urlBase;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<MercadoriaDto> listarMercadoriasPorEmpresa(long empresaId, String jwt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            if (jwt != null) headers.set("Authorization", jwt);
            HttpEntity<Void> entidade = new HttpEntity<>(headers);
            var resposta = restTemplate.exchange(urlBase + "/mercadoria/empresa/" + empresaId, HttpMethod.GET,
                    entidade, MercadoriaDto[].class);
            if (resposta.getStatusCode() == HttpStatus.NO_CONTENT || resposta.getBody() == null) return List.of();
            return Arrays.asList(resposta.getBody());
        } catch (Exception e) {
            return List.of();
        }
    }

    public List<ServicoDto> listarServicosPorEmpresa(long empresaId, String jwt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            if (jwt != null) headers.set("Authorization", jwt);
            HttpEntity<Void> entidade = new HttpEntity<>(headers);
            var resposta = restTemplate.exchange(urlBase + "/servico/empresa/" + empresaId, HttpMethod.GET,
                    entidade, ServicoDto[].class);
            if (resposta.getStatusCode() == HttpStatus.NO_CONTENT || resposta.getBody() == null) return List.of();
            return Arrays.asList(resposta.getBody());
        } catch (Exception e) {
            return List.of();
        }
    }
}
