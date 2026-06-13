package com.autobots.msempresa.servicos;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autobots.msempresa.cliente.CatalogoCliente;
import com.autobots.msempresa.cliente.UsuarioCliente;
import com.autobots.msempresa.cliente.VeiculoCliente;
import com.autobots.msempresa.cliente.VendaCliente;
import com.autobots.msempresa.dto.MercadoriaDto;
import com.autobots.msempresa.dto.ServicoDto;
import com.autobots.msempresa.dto.UsuarioDto;
import com.autobots.msempresa.dto.VeiculoDto;
import com.autobots.msempresa.dto.VendaDto;

@Service
public class ServicoEmpresaAgregado {

    @Autowired
    private UsuarioCliente usuarioCliente;
    @Autowired
    private CatalogoCliente catalogoCliente;
    @Autowired
    private VendaCliente vendaCliente;
    @Autowired
    private VeiculoCliente veiculoCliente;

    public List<UsuarioDto> listarClientes(long empresaId, String jwt) {
        return usuarioCliente.listarPorEmpresaEPerfil(empresaId, "ROLE_CLIENTE", jwt);
    }

    public List<UsuarioDto> listarFuncionarios(long empresaId, String jwt) {
        return usuarioCliente.listarPorEmpresaEPerfil(empresaId, null, jwt).stream()
                .filter(u -> u.getPerfis() != null && !u.getPerfis().contains("ROLE_CLIENTE"))
                .collect(Collectors.toList());
    }

    public List<MercadoriaDto> listarMercadorias(long empresaId, String jwt) {
        return catalogoCliente.listarMercadoriasPorEmpresa(empresaId, jwt);
    }

    public List<ServicoDto> listarServicos(long empresaId, String jwt) {
        return catalogoCliente.listarServicosPorEmpresa(empresaId, jwt);
    }

    public List<VendaDto> listarVendasPorPeriodo(String inicio, String fim, String jwt) {
        return vendaCliente.listarPorPeriodo(inicio, fim, jwt);
    }

    public List<VeiculoDto> listarVeiculos(long empresaId, String jwt) {
        List<VendaDto> vendas = vendaCliente.listarPorPeriodo("1900-01-01", "2999-12-31", jwt);
        Set<Long> vendaIds = vendas.stream().map(VendaDto::getId).collect(Collectors.toSet());
        return veiculoCliente.listarPorVendaIds(vendaIds, jwt);
    }
}
