package com.autobots.msusuario.modelo;

import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.autobots.msusuario.controles.DocumentoControle;
import com.autobots.msusuario.entidades.Documento;

@Component
public class AdicionadorLinkDocumento implements AdicionadorLink<Documento> {
    @Override
    public void adicionarLink(List<Documento> lista) {
        for (Documento item : lista) {
            Link selfLink = WebMvcLinkBuilder.linkTo(DocumentoControle.class).slash(item.getId()).withSelfRel();
            item.add(selfLink);
        }
    }
    @Override
    public void adicionarLink(Documento objeto) {
        Link selfLink = WebMvcLinkBuilder.linkTo(DocumentoControle.class).slash(objeto.getId()).withSelfRel();
        Link listaLink = WebMvcLinkBuilder.linkTo(DocumentoControle.class).withRel("documentos");
        objeto.add(selfLink);
        objeto.add(listaLink);
    }
}
