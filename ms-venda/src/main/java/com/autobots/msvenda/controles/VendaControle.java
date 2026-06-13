package com.autobots.msvenda.controles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.autobots.msvenda.dto.VendaCompostaDto;
import com.autobots.msvenda.entidades.Venda;
import com.autobots.msvenda.servicos.ServicoVenda;
import com.autobots.msvenda.servicos.ServicoVendaComposta;

@RestController
@RequestMapping("/venda")
public class VendaControle {
    @Autowired
    private ServicoVenda servico;

    @Autowired
    private ServicoVendaComposta servicoComposta;

    @GetMapping
    public ResponseEntity<List<Venda>> listar() {
        List<Venda> lista = servico.listar();
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscar(@PathVariable long id) {
        Venda venda = servico.buscar(id);
        if (venda == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(venda);
    }

    @GetMapping("/{id}/completa")
    public ResponseEntity<VendaCompostaDto> buscarCompleta(@PathVariable long id,
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        VendaCompostaDto dto = servicoComposta.compor(id, authorization);
        if (dto == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Venda>> listarPorPeriodo(@RequestParam String inicio, @RequestParam String fim) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dataInicio = formato.parse(inicio);
            Date dataFim = formato.parse(fim);
            List<Venda> lista = servico.listarPorPeriodo(dataInicio, dataFim);
            if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.ok(lista);
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Venda venda) {
        servico.cadastrar(venda);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable long id, @RequestBody Venda atualizacao) {
        atualizacao.setId(id);
        servico.atualizar(atualizacao);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable long id) {
        servico.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
