package com.autobots.msusuario.modelo;

import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.autobots.msusuario.controles.TelefoneControle;
import com.autobots.msusuario.entidades.Telefone;

@Component
public class AdicionadorLinkTelefone implements AdicionadorLink<Telefone> {
    @Override
    public void adicionarLink(List<Telefone> lista) {
        for (Telefone item : lista) {
            Link selfLink = WebMvcLinkBuilder.linkTo(TelefoneControle.class).slash(item.getId()).withSelfRel();
            item.add(selfLink);
        }
    }
    @Override
    public void adicionarLink(Telefone objeto) {
        Link selfLink = WebMvcLinkBuilder.linkTo(TelefoneControle.class).slash(objeto.getId()).withSelfRel();
        Link listaLink = WebMvcLinkBuilder.linkTo(TelefoneControle.class).withRel("telefones");
        objeto.add(selfLink);
        objeto.add(listaLink);
    }
}
