package com.autobots.msempresa.dto;

import java.util.Date;
import lombok.Data;

@Data
public class MercadoriaDto {
    private Long id;
    private Date validade;
    private Date fabricao;
    private Date cadastro;
    private String nome;
    private long quantidade;
    private double valor;
    private String descricao;
    private Long empresaId;
    private Long usuarioId;
}
