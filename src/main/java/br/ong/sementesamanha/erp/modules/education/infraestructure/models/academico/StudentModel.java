package br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.IndividualPersonModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "alunos", schema = "academico")
@Getter
@Setter
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private IndividualPersonModel person;

    @Column(name = "origem_inscricao_id")
    private Long registrationOriginId;

    @Column(name = "periodo_preferencial_id")
    private Long periodId;

    @Column(name = "possui_autonomia_transporte")
    private boolean hasTransportAutonomy;

    @Column(name = "responsavel_transporte")
    private String transportResponsibleName;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_matricula")
    private Date registrationDate;

    @Column(name = "ativo")
    private boolean active;

    @ManyToMany
    @JoinTable(
        name = "alunos_responsaveis",
        schema = "academico",
        joinColumns = @JoinColumn(name = "aluno_id"),
        inverseJoinColumns = @JoinColumn(name = "responsavel_id")
    )
    private Set<LegalGuardianModel> guardians;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private HomeConditionModel homeCondition;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private StudentHealthModel healthRecord;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DailyOccurrenceModel> occurrences;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WorkshopParticipantModel> workshopParticipations;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SocialInteractionModel> socialInteractions;
}
