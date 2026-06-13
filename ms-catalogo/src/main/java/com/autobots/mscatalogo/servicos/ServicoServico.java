package com.autobots.mscatalogo.servicos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autobots.mscatalogo.entidades.Servico;
import com.autobots.mscatalogo.modelo.AdicionadorLinkServico;
import com.autobots.mscatalogo.repositorios.ServicoRepositorio;

@Service
public class ServicoServico {
    @Autowired
    private ServicoRepositorio repositorio;
    @Autowired
    private AdicionadorLinkServico adicionadorLink;

    public List<Servico> listar() {
        List<Servico> lista = repositorio.findAll();
        adicionadorLink.adicionarLink(lista);
        return lista;
    }

    public Servico buscar(long id) {
        Servico servico = repositorio.findById(id).orElse(null);
        if (servico != null) adicionadorLink.adicionarLink(servico);
        return servico;
    }

    public List<Servico> listarPorEmpresa(long empresaId) {
        List<Servico> lista = repositorio.findByEmpresaId(empresaId);
        adicionadorLink.adicionarLink(lista);
        return lista;
    }

    public void cadastrar(Servico servico) {
        repositorio.save(servico);
    }

    public void atualizar(Servico atualizacao) {
        Servico servico = repositorio.findById(atualizacao.getId()).orElseThrow();
        if (atualizacao.getNome() != null && !atualizacao.getNome().isBlank())
            servico.setNome(atualizacao.getNome());
        if (atualizacao.getDescricao() != null)
            servico.setDescricao(atualizacao.getDescricao());
        if (atualizacao.getValor() > 0)
            servico.setValor(atualizacao.getValor());
        if (atualizacao.getEmpresaId() != null)
            servico.setEmpresaId(atualizacao.getEmpresaId());
        repositorio.save(servico);
    }

    public void excluir(long id) {
        repositorio.deleteById(id);
    }
}
