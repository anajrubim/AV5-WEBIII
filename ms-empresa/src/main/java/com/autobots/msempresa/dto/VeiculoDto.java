package com.autobots.msempresa.dto;

import lombok.Data;

@Data
public class VeiculoDto {
    private Long id;
    private String tipo;
    private String modelo;
    private String placa;
    private Long proprietarioId;
}
