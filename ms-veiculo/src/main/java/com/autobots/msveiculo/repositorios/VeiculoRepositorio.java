package com.autobots.msveiculo.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.msveiculo.entidades.Veiculo;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByVendaIdsIn(List<Long> vendaIds);
}
