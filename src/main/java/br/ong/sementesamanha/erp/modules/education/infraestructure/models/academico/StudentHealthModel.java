package br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "prontuarios_saude", schema = "academico")
@Getter
@Setter
public class StudentHealthModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private StudentModel student;

    @Column(name = "ubs_referencia")
    private String ubsReference;

    @Column(name = "usa_oculos")
    private Boolean wearsGlasses;

    @Column(name = "data_vencimento_info")
    private LocalDate infoExpirationDate;

    @OneToMany(mappedBy = "healthRecord", cascade = CascadeType.ALL)
    private Set<StudentMedicationModel> medications;

    @OneToMany(mappedBy = "healthRecord", cascade = CascadeType.ALL)
    private Set<MedicalTreatmentModel> treatments;
}
