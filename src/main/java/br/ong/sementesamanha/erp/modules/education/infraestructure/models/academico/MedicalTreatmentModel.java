package br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tratamentos_medicos", schema = "academico")
@Getter
@Setter
public class MedicalTreatmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prontuario_id")
    private StudentHealthModel healthRecord;

    @Column(name = "descricao_tratamento")
    private String treatmentDescription;

    @Column(name = "observacoes")
    private String observations;

    @Column(name = "local_acompanhamento_id")
    private Long monitoringLocationId;
}
