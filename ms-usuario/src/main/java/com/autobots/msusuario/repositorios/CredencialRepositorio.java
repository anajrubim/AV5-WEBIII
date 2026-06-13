package com.autobots.msusuario.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.msusuario.entidades.Credencial;

public interface CredencialRepositorio extends JpaRepository<Credencial, Long> {
}
