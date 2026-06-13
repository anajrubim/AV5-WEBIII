package com.autobots.msempresa.dto;

import java.util.Set;
import lombok.Data;

@Data
public class UsuarioDto {
    private Long id;
    private String nome;
    private String nomeSocial;
    private Long empresaId;
    private Set<String> perfis;
}
