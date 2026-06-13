package com.autobots.msvenda.servicos;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autobots.msvenda.entidades.Venda;
import com.autobots.msvenda.modelo.AdicionadorLinkVenda;
import com.autobots.msvenda.repositorios.VendaRepositorio;

@Service
public class ServicoVenda {
    @Autowired
    private VendaRepositorio repositorio;
    @Autowired
    private AdicionadorLinkVenda adicionadorLink;

    public List<Venda> listar() {
        List<Venda> lista = repositorio.findAll();
        adicionadorLink.adicionarLink(lista);
        return lista;
    }

    public Venda buscar(long id) {
        Venda venda = repositorio.findById(id).orElse(null);
        if (venda != null) adicionadorLink.adicionarLink(venda);
        return venda;
    }

    public List<Venda> listarPorPeriodo(Date inicio, Date fim) {
        List<Venda> lista = repositorio.findByPeriodo(inicio, fim);
        adicionadorLink.adicionarLink(lista);
        return lista;
    }

    public void cadastrar(Venda venda) {
        if (venda.getCadastro() == null) {
            venda.setCadastro(new Date());
        }
        repositorio.save(venda);
    }

    public void atualizar(Venda atualizacao) {
        Venda venda = repositorio.findById(atualizacao.getId()).orElseThrow();
        if (atualizacao.getIdentificacao() != null && !atualizacao.getIdentificacao().isBlank())
            venda.setIdentificacao(atualizacao.getIdentificacao());
        if (atualizacao.getClienteId() != null)
            venda.setClienteId(atualizacao.getClienteId());
        if (atualizacao.getFuncionarioId() != null)
            venda.setFuncionarioId(atualizacao.getFuncionarioId());
        if (atualizacao.getVeiculoId() != null)
            venda.setVeiculoId(atualizacao.getVeiculoId());
        if (atualizacao.getMercadoriaIds() != null && !atualizacao.getMercadoriaIds().isEmpty())
            venda.getMercadoriaIds().addAll(atualizacao.getMercadoriaIds());
        if (atualizacao.getServicoIds() != null && !atualizacao.getServicoIds().isEmpty())
            venda.getServicoIds().addAll(atualizacao.getServicoIds());
        repositorio.save(venda);
    }

    public void excluir(long id) {
        repositorio.deleteById(id);
    }
}
