package com.autobots.msvenda.dto;

import lombok.Data;

@Data
public class ServicoDto {
    private Long id;
    private String nome;
    private double valor;
    private String descricao;
}
