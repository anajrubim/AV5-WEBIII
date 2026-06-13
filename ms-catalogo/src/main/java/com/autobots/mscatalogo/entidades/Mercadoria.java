package com.autobots.mscatalogo.entidades;

import java.util.Date;
import javax.persistence.*;
import org.springframework.hateoas.RepresentationModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Mercadoria extends RepresentationModel<Mercadoria> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date validade;
    @Column(nullable = false)
    private Date fabricao;
    @Column(nullable = false)
    private Date cadastro;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private long quantidade;
    @Column(nullable = false)
    private double valor;
    @Column
    private String descricao;
    @Column
    private Long empresaId;
    @Column
    private Long usuarioId;
}
