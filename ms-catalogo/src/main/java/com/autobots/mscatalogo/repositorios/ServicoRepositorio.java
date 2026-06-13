package com.autobots.mscatalogo.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.mscatalogo.entidades.Servico;

public interface ServicoRepositorio extends JpaRepository<Servico, Long> {
    List<Servico> findByEmpresaId(Long empresaId);
}
