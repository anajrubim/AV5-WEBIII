package com.autobots.msusuario.entidades;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.springframework.hateoas.RepresentationModel;
import com.autobots.msusuario.enumeracoes.PerfilUsuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, exclude = { "mercadoriaIds", "vendaIds", "veiculoIds" })
@Entity
public class Usuario extends RepresentationModel<Usuario> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column
    private String nomeSocial;

    /** Id da empresa (loja) a qual este usuario pertence. Referencia para o ms-empresa. */
    @Column
    private Long empresaId;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<PerfilUsuario> perfis = new HashSet<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Telefone> telefones = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Documento> documentos = new HashSet<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Email> emails = new HashSet<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Credencial> credenciais = new HashSet<>();

    /** Ids das mercadorias cadastradas por este usuario (microsservico ms-catalogo) */
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> mercadoriaIds = new HashSet<>();

    /** Ids das vendas em que este usuario participa (microsservico ms-venda) */
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> vendaIds = new HashSet<>();

    /** Ids dos veiculos pertencentes a este usuario (microsservico ms-veiculo) */
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> veiculoIds = new HashSet<>();
}
