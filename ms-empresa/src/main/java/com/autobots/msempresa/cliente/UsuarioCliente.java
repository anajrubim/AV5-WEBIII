package com.autobots.msempresa.cliente;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.autobots.msempresa.dto.UsuarioDto;

@Component
public class UsuarioCliente {

    @Value("${ms.usuario.url}")
    private String urlBase;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<UsuarioDto> listarPorEmpresaEPerfil(long empresaId, String perfil, String jwt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            if (jwt != null) headers.set("Authorization", jwt);
            HttpEntity<Void> entidade = new HttpEntity<>(headers);
            UsuarioDto[] usuarios = restTemplate.exchange(urlBase + "/usuario", HttpMethod.GET, entidade,
                    UsuarioDto[].class).getBody();
            if (usuarios == null) return List.of();
            return Arrays.stream(usuarios)
                    .filter(u -> empresaId == 0 || (u.getEmpresaId() != null && u.getEmpresaId() == empresaId))
                    .filter(u -> perfil == null || (u.getPerfis() != null && u.getPerfis().contains(perfil)))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return List.of();
        }
    }
}
