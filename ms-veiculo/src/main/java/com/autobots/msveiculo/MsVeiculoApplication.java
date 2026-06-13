package com.autobots.msveiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.autobots.msveiculo.entidades.Veiculo;
import com.autobots.msveiculo.enumeracoes.TipoVeiculo;
import com.autobots.msveiculo.repositorios.VeiculoRepositorio;

@SpringBootApplication
public class MsVeiculoApplication implements CommandLineRunner {

    @Autowired
    private VeiculoRepositorio repositorio;

    public static void main(String[] args) {
        SpringApplication.run(MsVeiculoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Veiculo corollaCross = new Veiculo();
        corollaCross.setModelo("Corolla Cross");
        corollaCross.setPlaca("ABC-0000");
        corollaCross.setTipo(TipoVeiculo.SUV);
        corollaCross.setProprietarioId(4L);
        corollaCross.getVendaIds().add(1L);
        repositorio.save(corollaCross);
    }
}
