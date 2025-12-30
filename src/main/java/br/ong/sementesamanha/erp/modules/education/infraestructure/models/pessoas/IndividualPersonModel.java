package br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "fisicas", schema = "pessoas")
@Getter
@Setter
public class IndividualPersonModel {
    @Id
    @Column(name = "pessoa_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "pessoa_id")
    private PersonModel person;

    @Column(name = "nome")
    private String personName;

    @Column(name = "naturalidade_id")
    private Long naturalnessId;

    @Column(name = "raca_id")
    private Long raceId;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date birthDate;

    @Column(name = "sexo_id")
    private Long sexId;

    @Column(name = "nome_mae")
    private String motherName;

    @Column(name = "nome_pai")
    private String fatherName;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PersonAddressModel address;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PersonContactModel contact;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private IndividualPersonEducationModel education;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PersonDocumentModel> documents;
}
