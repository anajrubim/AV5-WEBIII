package com.autobots.msusuario.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.msusuario.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
