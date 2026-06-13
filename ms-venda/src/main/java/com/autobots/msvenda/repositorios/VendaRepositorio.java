package com.autobots.msvenda.repositorios;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.autobots.msvenda.entidades.Venda;

public interface VendaRepositorio extends JpaRepository<Venda, Long> {

    @Query("SELECT v FROM Venda v WHERE v.cadastro BETWEEN :inicio AND :fim")
    List<Venda> findByPeriodo(@Param("inicio") Date inicio, @Param("fim") Date fim);
}
