package com.autobots.msvenda;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.autobots.msvenda.entidades.Venda;
import com.autobots.msvenda.repositorios.VendaRepositorio;

@SpringBootApplication
public class MsVendaApplication implements CommandLineRunner {

    @Autowired
    private VendaRepositorio repositorio;

    public static void main(String[] args) {
        SpringApplication.run(MsVendaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Venda venda = new Venda();
        venda.setCadastro(new Date());
        venda.setIdentificacao("1234698745");
        venda.setClienteId(4L);
        venda.setFuncionarioId(3L);
        venda.getMercadoriaIds().add(1L);
        venda.getServicoIds().add(1L);
        venda.getServicoIds().add(2L);
        venda.setVeiculoId(1L);
        repositorio.save(venda);
    }
}
