package br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "participantes_oficina", schema = "academico")
@Getter
@Setter
public class WorkshopParticipantModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oficina_id")
    private WorkshopModel workshop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private StudentModel student;

    @Column(name = "data_inscricao")
    private LocalDate registrationDate;
}
