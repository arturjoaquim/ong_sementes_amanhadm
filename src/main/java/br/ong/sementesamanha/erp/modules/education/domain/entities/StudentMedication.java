package br.ong.sementesamanha.erp.modules.education.domain.entities;

import br.ong.sementesamanha.erp.modules.education.domain.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medicamentos_aluno", schema = "academico")
@Getter
@Setter
public class StudentMedication extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prontuario_id")
    private StudentHealth healthRecord;

    @Column(name = "nome_medicamento")
    private String medicationName;

    @Column(name = "frequencia")
    private String frequency;

    @Column(name = "dosagem")
    private String dosage;
}
