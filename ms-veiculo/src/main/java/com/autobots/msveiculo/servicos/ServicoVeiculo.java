package com.autobots.msveiculo.servicos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autobots.msveiculo.entidades.Veiculo;
import com.autobots.msveiculo.modelo.AdicionadorLinkVeiculo;
import com.autobots.msveiculo.repositorios.VeiculoRepositorio;

@Service
public class ServicoVeiculo {
    @Autowired
    private VeiculoRepositorio repositorio;
    @Autowired
    private AdicionadorLinkVeiculo adicionadorLink;

    public List<Veiculo> listar() {
        List<Veiculo> lista = repositorio.findAll();
        adicionadorLink.adicionarLink(lista);
        return lista;
    }

    public Veiculo buscar(long id) {
        Veiculo veiculo = repositorio.findById(id).orElse(null);
        if (veiculo != null) adicionadorLink.adicionarLink(veiculo);
        return veiculo;
    }

    public List<Veiculo> listarPorVendas(List<Long> vendaIds) {
        List<Veiculo> lista = repositorio.findByVendaIdsIn(vendaIds);
        adicionadorLink.adicionarLink(lista);
        return lista;
    }

    public void cadastrar(Veiculo veiculo) {
        repositorio.save(veiculo);
    }

    public void atualizar(Veiculo atualizacao) {
        Veiculo veiculo = repositorio.findById(atualizacao.getId()).orElseThrow();
        if (atualizacao.getModelo() != null && !atualizacao.getModelo().isBlank())
            veiculo.setModelo(atualizacao.getModelo());
        if (atualizacao.getPlaca() != null && !atualizacao.getPlaca().isBlank())
            veiculo.setPlaca(atualizacao.getPlaca());
        if (atualizacao.getTipo() != null)
            veiculo.setTipo(atualizacao.getTipo());
        if (atualizacao.getProprietarioId() != null)
            veiculo.setProprietarioId(atualizacao.getProprietarioId());
        if (atualizacao.getVendaIds() != null && !atualizacao.getVendaIds().isEmpty())
            veiculo.getVendaIds().addAll(atualizacao.getVendaIds());
        repositorio.save(veiculo);
    }

    public void excluir(long id) {
        repositorio.deleteById(id);
    }
}
