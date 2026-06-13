package com.autobots.msvenda.dto;

import lombok.Data;

@Data
public class MercadoriaDto {
    private Long id;
    private String nome;
    private double valor;
    private String descricao;
}
