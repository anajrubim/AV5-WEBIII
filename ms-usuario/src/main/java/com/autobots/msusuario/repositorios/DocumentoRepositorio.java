package com.autobots.msusuario.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.msusuario.entidades.Documento;

public interface DocumentoRepositorio extends JpaRepository<Documento, Long> {
}
