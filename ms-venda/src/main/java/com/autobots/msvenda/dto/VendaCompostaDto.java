package com.autobots.msvenda.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class VendaCompostaDto {
    private Long id;
    private Date cadastro;
    private String identificacao;
    private UsuarioDto cliente;
    private UsuarioDto funcionario;
    private List<MercadoriaDto> mercadorias;
    private List<ServicoDto> servicos;
    private VeiculoDto veiculo;
}
