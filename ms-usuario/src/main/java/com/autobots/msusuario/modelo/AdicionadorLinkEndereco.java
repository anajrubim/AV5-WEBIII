package com.autobots.msusuario.modelo;

import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.autobots.msusuario.controles.EnderecoControle;
import com.autobots.msusuario.entidades.Endereco;

@Component
public class AdicionadorLinkEndereco implements AdicionadorLink<Endereco> {
    @Override
    public void adicionarLink(List<Endereco> lista) {
        for (Endereco item : lista) {
            Link selfLink = WebMvcLinkBuilder.linkTo(EnderecoControle.class).slash(item.getId()).withSelfRel();
            item.add(selfLink);
        }
    }
    @Override
    public void adicionarLink(Endereco objeto) {
        Link selfLink = WebMvcLinkBuilder.linkTo(EnderecoControle.class).slash(objeto.getId()).withSelfRel();
        Link listaLink = WebMvcLinkBuilder.linkTo(EnderecoControle.class).withRel("enderecos");
        objeto.add(selfLink);
        objeto.add(listaLink);
    }
}
