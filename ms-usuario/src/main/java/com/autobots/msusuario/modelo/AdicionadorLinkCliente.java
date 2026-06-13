package com.autobots.msusuario.modelo;

import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.autobots.msusuario.controles.ClienteControle;
import com.autobots.msusuario.entidades.Cliente;

@Component
public class AdicionadorLinkCliente implements AdicionadorLink<Cliente> {
    @Override
    public void adicionarLink(List<Cliente> lista) {
        for (Cliente item : lista) {
            Link selfLink = WebMvcLinkBuilder.linkTo(ClienteControle.class).slash(item.getId()).withSelfRel();
            item.add(selfLink);
        }
    }
    @Override
    public void adicionarLink(Cliente objeto) {
        Link selfLink = WebMvcLinkBuilder.linkTo(ClienteControle.class).slash(objeto.getId()).withSelfRel();
        Link listaLink = WebMvcLinkBuilder.linkTo(ClienteControle.class).withRel("clientes");
        objeto.add(selfLink);
        objeto.add(listaLink);
    }
}
