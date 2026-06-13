package com.autobots.mscatalogo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.autobots.mscatalogo.entidades.Mercadoria;
import com.autobots.mscatalogo.entidades.Servico;
import com.autobots.mscatalogo.repositorios.MercadoriaRepositorio;
import com.autobots.mscatalogo.repositorios.ServicoRepositorio;

@SpringBootApplication
public class MsCatalogoApplication implements CommandLineRunner {

    @Autowired
    private MercadoriaRepositorio repositorioMercadoria;

    @Autowired
    private ServicoRepositorio repositorioServico;

    public static void main(String[] args) {
        SpringApplication.run(MsCatalogoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Mercadoria rodaLigaLeve = new Mercadoria();
        rodaLigaLeve.setCadastro(new Date());
        rodaLigaLeve.setFabricao(new Date());
        rodaLigaLeve.setNome("Roda de liga leve modelo Toyota Etios");
        rodaLigaLeve.setValidade(new Date());
        rodaLigaLeve.setQuantidade(30);
        rodaLigaLeve.setValor(300.0);
        rodaLigaLeve.setDescricao("Roda de liga leve original de fábrica Toyota para modelos hatch");
        rodaLigaLeve.setEmpresaId(1L);
        rodaLigaLeve.setUsuarioId(3L);
        repositorioMercadoria.save(rodaLigaLeve);

        Servico trocaRodas = new Servico();
        trocaRodas.setDescricao("Troca das rodas do carro por novas");
        trocaRodas.setNome("Troca de Rodas");
        trocaRodas.setValor(50);
        trocaRodas.setEmpresaId(1L);
        repositorioServico.save(trocaRodas);

        Servico alinhamento = new Servico();
        alinhamento.setDescricao("Alinhamento das rodas do carro");
        alinhamento.setNome("Alinhamento de Rodas");
        alinhamento.setValor(50);
        alinhamento.setEmpresaId(1L);
        repositorioServico.save(alinhamento);
    }
}
