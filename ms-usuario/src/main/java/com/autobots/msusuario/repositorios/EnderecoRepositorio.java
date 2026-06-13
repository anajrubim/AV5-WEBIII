package com.autobots.msusuario.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.msusuario.entidades.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {
}
