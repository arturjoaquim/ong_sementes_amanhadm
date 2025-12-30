package br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "juridicas", schema = "pessoas")
@Getter
@Setter
public class LegalPersonModel {
    @Id
    @Column(name = "pessoa_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pessoa_id")
    private PersonModel person;

    @Column(name = "razao_social")
    private String corporateName;

    @Column(name = "nome_fantasia")
    private String tradeName;
}
