package com.autobots.msvenda.servicos;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autobots.msvenda.cliente.CatalogoCliente;
import com.autobots.msvenda.cliente.UsuarioCliente;
import com.autobots.msvenda.cliente.VeiculoCliente;
import com.autobots.msvenda.dto.VendaCompostaDto;
import com.autobots.msvenda.entidades.Venda;

@Service
public class ServicoVendaComposta {

    @Autowired
    private ServicoVenda servicoVenda;
    @Autowired
    private UsuarioCliente usuarioCliente;
    @Autowired
    private CatalogoCliente catalogoCliente;
    @Autowired
    private VeiculoCliente veiculoCliente;

    public VendaCompostaDto compor(long id, String jwt) {
        Venda venda = servicoVenda.buscar(id);
        if (venda == null) return null;

        VendaCompostaDto dto = new VendaCompostaDto();
        dto.setId(venda.getId());
        dto.setCadastro(venda.getCadastro());
        dto.setIdentificacao(venda.getIdentificacao());

        dto.setCliente(usuarioCliente.buscar(venda.getClienteId(), jwt));
        dto.setFuncionario(usuarioCliente.buscar(venda.getFuncionarioId(), jwt));
        dto.setVeiculo(veiculoCliente.buscar(venda.getVeiculoId(), jwt));

        dto.setMercadorias(venda.getMercadoriaIds().stream()
                .map(idMercadoria -> catalogoCliente.buscarMercadoria(idMercadoria, jwt))
                .filter(m -> m != null)
                .collect(Collectors.toList()));

        dto.setServicos(venda.getServicoIds().stream()
                .map(idServico -> catalogoCliente.buscarServico(idServico, jwt))
                .filter(s -> s != null)
                .collect(Collectors.toList()));

        return dto;
    }
}
