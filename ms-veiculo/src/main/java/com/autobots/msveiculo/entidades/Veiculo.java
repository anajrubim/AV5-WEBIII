package com.autobots.msveiculo.entidades;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import org.springframework.hateoas.RepresentationModel;
import com.autobots.msveiculo.enumeracoes.TipoVeiculo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Veiculo extends RepresentationModel<Veiculo> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private TipoVeiculo tipo;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false)
    private String placa;
    @Column
    private Long proprietarioId;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> vendaIds = new HashSet<>();
}
