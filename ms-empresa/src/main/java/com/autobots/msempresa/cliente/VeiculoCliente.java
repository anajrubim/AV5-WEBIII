package com.autobots.msempresa.cliente;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.autobots.msempresa.dto.VeiculoDto;

@Component
public class VeiculoCliente {

    @Value("${ms.veiculo.url}")
    private String urlBase;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<VeiculoDto> listarPorVendaIds(Set<Long> vendaIds, String jwt) {
        if (vendaIds == null || vendaIds.isEmpty()) return List.of();
        try {
            HttpHeaders headers = new HttpHeaders();
            if (jwt != null) headers.set("Authorization", jwt);
            HttpEntity<Void> entidade = new HttpEntity<>(headers);
            String ids = vendaIds.stream().map(String::valueOf).collect(Collectors.joining(","));
            String url = urlBase + "/veiculo/venda?ids=" + ids;
            var resposta = restTemplate.exchange(url, HttpMethod.GET, entidade, VeiculoDto[].class);
            if (resposta.getBody() == null) return List.of();
            return Arrays.asList(resposta.getBody());
        } catch (Exception e) {
            return List.of();
        }
    }
}
