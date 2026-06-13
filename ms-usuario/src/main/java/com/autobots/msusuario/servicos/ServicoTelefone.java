package com.autobots.msusuario.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.msusuario.entidades.Telefone;
import com.autobots.msusuario.modelo.AdicionadorLinkTelefone;
import com.autobots.msusuario.modelo.TelefoneAtualizador;
import com.autobots.msusuario.repositorios.TelefoneRepositorio;

@Service
public class ServicoTelefone {

    @Autowired
    private TelefoneRepositorio repositorio;

    @Autowired
    private AdicionadorLinkTelefone adicionadorLink;

    public List<Telefone> listar() {
        List<Telefone> lista = repositorio.findAll();
        adicionadorLink.adicionarLink(lista);
        return lista;
    }

    public Telefone buscar(long id) {
        Telefone telefone = repositorio.findById(id).orElse(null);
        if (telefone != null) adicionadorLink.adicionarLink(telefone);
        return telefone;
    }

    public void cadastrar(Telefone telefone) {
        repositorio.save(telefone);
    }

    public void atualizar(Telefone atualizacao) {
        Telefone telefone = repositorio.getById(atualizacao.getId());
        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(telefone, atualizacao);
        repositorio.save(telefone);
    }

    public void excluir(long id) {
        repositorio.deleteById(id);
    }
}
