package br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "escolaridade", schema = "pessoas")
@Getter
@Setter
public class IndividualPersonEducationModel {
    @Id
    @Column(name = "pessoa_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "pessoa_id")
    private IndividualPersonModel person;

    @Column(name = "instituicao_nome")
    private String institution;

    @Column(name = "periodo_id")
    private Long periodId;

    @Column(name = "nivel_ensino_id")
    private Long educationLevelId;

    @Column(name = "status_ensino_id")
    private Long educationStatusId;
}
