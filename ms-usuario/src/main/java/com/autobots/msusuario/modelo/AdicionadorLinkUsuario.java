package com.autobots.msusuario.modelo;

import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.autobots.msusuario.controles.UsuarioControle;
import com.autobots.msusuario.entidades.Usuario;

@Component
public class AdicionadorLinkUsuario implements AdicionadorLink<Usuario> {
    @Override
    public void adicionarLink(List<Usuario> lista) {
        for (Usuario item : lista) {
            Link selfLink = WebMvcLinkBuilder.linkTo(UsuarioControle.class).slash(item.getId()).withSelfRel();
            item.add(selfLink);
        }
    }
    @Override
    public void adicionarLink(Usuario objeto) {
        Link selfLink = WebMvcLinkBuilder.linkTo(UsuarioControle.class).slash(objeto.getId()).withSelfRel();
        Link listaLink = WebMvcLinkBuilder.linkTo(UsuarioControle.class).withRel("usuarios");
        objeto.add(selfLink);
        objeto.add(listaLink);
    }
}
