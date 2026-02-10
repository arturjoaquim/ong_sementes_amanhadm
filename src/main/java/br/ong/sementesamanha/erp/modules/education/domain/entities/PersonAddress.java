package br.ong.sementesamanha.erp.modules.education.domain.entities;

import br.ong.sementesamanha.erp.modules.education.domain.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enderecos", schema = "pessoas")
@Getter
@Setter
public class PersonAddress extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private Person person;

    @Column(name = "cep")
    private String cep;

    @Column(name = "numero")
    private String streetNumber;

    @Column(name = "logradouro")
    private String street;

    @Column(name = "bairro")
    private String neighborhood;

    @Column(name = "cidade_nome")
    private String city;

    @Column(name = "uf_sigla", columnDefinition = "bpchar(2)")
    private String uf;

    @Column(name = "complemento")
    private String complement;
}
