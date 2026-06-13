package com.autobots.msusuario.cliente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmpresaCliente {

    @Value("${ms.empresa.url}")
    private String urlBase;

    private final RestTemplate restTemplate = new RestTemplate();

    public void removerUsuarioDeTodasEmpresas(long usuarioId) {
        try {
            String url = urlBase + "/empresa/usuarios/" + usuarioId;
            restTemplate.execute(url, HttpMethod.DELETE, null, null);
        } catch (Exception e) {
        }
    }
}
