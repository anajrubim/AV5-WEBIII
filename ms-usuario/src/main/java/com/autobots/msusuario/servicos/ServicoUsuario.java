package com.autobots.msusuario.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.msusuario.cliente.EmpresaCliente;
import com.autobots.msusuario.entidades.Usuario;
import com.autobots.msusuario.modelo.AdicionadorLinkUsuario;
import com.autobots.msusuario.repositorios.UsuarioRepositorio;

@Service
public class ServicoUsuario {

    @Autowired
    private UsuarioRepositorio repositorio;

    @Autowired
    private AdicionadorLinkUsuario adicionadorLink;

    @Autowired
    private EmpresaCliente empresaCliente;

    public List<Usuario> listar() {
        List<Usuario> lista = repositorio.findAll();
        adicionadorLink.adicionarLink(lista);
        return lista;
    }

    public Usuario buscar(long id) {
        Usuario usuario = repositorio.findById(id).orElse(null);
        if (usuario != null) {
            adicionadorLink.adicionarLink(usuario);
        }
        return usuario;
    }

    public void cadastrar(Usuario usuario) {
        repositorio.save(usuario);
    }

    public void atualizar(Usuario atualizacao) {
        Usuario usuario = repositorio.findById(atualizacao.getId()).orElseThrow();

        if (atualizacao.getNome() != null && !atualizacao.getNome().isBlank()) {
            usuario.setNome(atualizacao.getNome());
        }

        if (atualizacao.getNomeSocial() != null && !atualizacao.getNomeSocial().isBlank()) {
            usuario.setNomeSocial(atualizacao.getNomeSocial());
        }

        if (atualizacao.getPerfis() != null && !atualizacao.getPerfis().isEmpty()) {
            usuario.setPerfis(atualizacao.getPerfis());
        }

        if (atualizacao.getEmpresaId() != null) {
            usuario.setEmpresaId(atualizacao.getEmpresaId());
        }

        repositorio.save(usuario);
    }

    public void excluir(long id) {
        Usuario usuario = repositorio.findById(id).orElseThrow();
        
        empresaCliente.removerUsuarioDeTodasEmpresas(id);

        repositorio.delete(usuario);
    }
}
