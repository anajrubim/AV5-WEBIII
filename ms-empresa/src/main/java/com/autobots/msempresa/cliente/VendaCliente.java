package com.autobots.msempresa.cliente;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.autobots.msempresa.dto.VendaDto;

@Component
public class VendaCliente {

    @Value("${ms.venda.url}")
    private String urlBase;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<VendaDto> listarPorPeriodo(String inicio, String fim, String jwt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            if (jwt != null) headers.set("Authorization", jwt);
            HttpEntity<Void> entidade = new HttpEntity<>(headers);
            String url = urlBase + "/venda/periodo?inicio=" + inicio + "&fim=" + fim;
            var resposta = restTemplate.exchange(url, HttpMethod.GET, entidade, VendaDto[].class);
            if (resposta.getBody() == null) return List.of();
            return Arrays.asList(resposta.getBody());
        } catch (Exception e) {
            return List.of();
        }
    }
}
