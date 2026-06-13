package com.autobots.msusuario.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.msusuario.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}
