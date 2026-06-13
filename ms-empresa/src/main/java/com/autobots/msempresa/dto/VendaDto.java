package com.autobots.msempresa.dto;

import java.util.Date;
import java.util.Set;
import lombok.Data;

@Data
public class VendaDto {
    private Long id;
    private Date cadastro;
    private String identificacao;
    private Long clienteId;
    private Long funcionarioId;
    private Set<Long> mercadoriaIds;
    private Set<Long> servicoIds;
    private Long veiculoId;
}
