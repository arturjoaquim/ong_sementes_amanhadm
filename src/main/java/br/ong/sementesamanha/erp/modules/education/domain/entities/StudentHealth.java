package br.ong.sementesamanha.erp.modules.education.domain.entities;

import br.ong.sementesamanha.erp.modules.education.domain.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "prontuarios_saude", schema = "academico")
@Getter
@Setter
public class StudentHealth extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Student student;

    @Column(name = "ubs_referencia")
    private String ubsReference;

    @Column(name = "usa_oculos")
    private Boolean wearsGlasses;

    @Column(name = "data_vencimento_info")
    private LocalDate infoExpirationDate;

    @OneToMany(mappedBy = "healthRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentMedication> medications;

    @OneToMany(mappedBy = "healthRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MedicalTreatment> treatments;
}
