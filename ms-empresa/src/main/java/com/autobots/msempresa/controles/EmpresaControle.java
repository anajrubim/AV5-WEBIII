package com.autobots.msempresa.controles;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.autobots.msempresa.dto.MercadoriaDto;
import com.autobots.msempresa.dto.ServicoDto;
import com.autobots.msempresa.dto.UsuarioDto;
import com.autobots.msempresa.dto.VeiculoDto;
import com.autobots.msempresa.dto.VendaDto;
import com.autobots.msempresa.entidades.Empresa;
import com.autobots.msempresa.servicos.ServicoEmpresa;
import com.autobots.msempresa.servicos.ServicoEmpresaAgregado;

@RestController
@RequestMapping("/empresa")
public class EmpresaControle {

    @Autowired
    private ServicoEmpresa servico;

    @Autowired
    private ServicoEmpresaAgregado servicoAgregado;

    @GetMapping
    public ResponseEntity<List<Empresa>> listar() {
        List<Empresa> lista = servico.listar();
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscar(@PathVariable long id) {
        Empresa empresa = servico.buscar(id);
        if (empresa == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(empresa);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Empresa empresa) {
        servico.cadastrar(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable long id, @RequestBody Empresa atualizacao) {
        atualizacao.setId(id);
        servico.atualizar(atualizacao);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable long id) {
        servico.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/clientes")
    public ResponseEntity<List<UsuarioDto>> listarClientesPorEmpresa(@PathVariable long id,
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        List<UsuarioDto> clientes = servicoAgregado.listarClientes(id, authorization);
        if (clientes.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}/funcionarios")
    public ResponseEntity<List<UsuarioDto>> listarFuncionariosPorEmpresa(@PathVariable long id,
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        List<UsuarioDto> funcionarios = servicoAgregado.listarFuncionarios(id, authorization);
        if (funcionarios.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}/mercadorias")
    public ResponseEntity<List<MercadoriaDto>> listarMercadoriasPorEmpresa(@PathVariable long id,
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        List<MercadoriaDto> mercadorias = servicoAgregado.listarMercadorias(id, authorization);
        if (mercadorias.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(mercadorias);
    }

    @GetMapping("/{id}/servicos")
    public ResponseEntity<List<ServicoDto>> listarServicosPorEmpresa(@PathVariable long id,
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        List<ServicoDto> servicos = servicoAgregado.listarServicos(id, authorization);
        if (servicos.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/{id}/vendas")
    public ResponseEntity<List<VendaDto>> listarVendasPorPeriodo(@PathVariable long id,
            @RequestParam String inicio, @RequestParam String fim,
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        List<VendaDto> vendas = servicoAgregado.listarVendasPorPeriodo(inicio, fim, authorization);
        if (vendas.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}/veiculos")
    public ResponseEntity<List<VeiculoDto>> listarVeiculosPorEmpresa(@PathVariable long id,
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        List<VeiculoDto> veiculos = servicoAgregado.listarVeiculos(id, authorization);
        if (veiculos.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(veiculos);
    }

    @DeleteMapping("/usuarios/{usuarioId}")
    public ResponseEntity<Void> removerUsuario(@PathVariable long usuarioId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
