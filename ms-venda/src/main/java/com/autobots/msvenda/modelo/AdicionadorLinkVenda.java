package com.autobots.msvenda.modelo;

import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.autobots.msvenda.controles.VendaControle;
import com.autobots.msvenda.entidades.Venda;

@Component
public class AdicionadorLinkVenda implements AdicionadorLink<Venda> {
    @Override
    public void adicionarLink(List<Venda> lista) {
        for (Venda venda : lista) {
            Link selfLink = WebMvcLinkBuilder.linkTo(VendaControle.class).slash(venda.getId()).withSelfRel();
            venda.add(selfLink);
        }
    }
    @Override
    public void adicionarLink(Venda objeto) {
        Link selfLink = WebMvcLinkBuilder.linkTo(VendaControle.class).slash(objeto.getId()).withSelfRel();
        Link listaLink = WebMvcLinkBuilder.linkTo(VendaControle.class).withRel("vendas");
        objeto.add(selfLink);
        objeto.add(listaLink);
    }
}
