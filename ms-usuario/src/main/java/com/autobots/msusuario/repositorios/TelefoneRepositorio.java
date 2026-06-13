package com.autobots.msusuario.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.msusuario.entidades.Telefone;

public interface TelefoneRepositorio extends JpaRepository<Telefone, Long> {
}
