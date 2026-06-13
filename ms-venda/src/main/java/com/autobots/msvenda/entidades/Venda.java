package com.autobots.msvenda.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.springframework.hateoas.RepresentationModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Venda extends RepresentationModel<Venda> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date cadastro;
    @Column(nullable = false, unique = true)
    private String identificacao;
    @Column
    private Long clienteId;
    @Column
    private Long funcionarioId;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> mercadoriaIds = new HashSet<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> servicoIds = new HashSet<>();
    @Column
    private Long veiculoId;
}
