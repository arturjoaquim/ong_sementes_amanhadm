package br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "participantes_oficina", schema = "academico")
@Getter
@Setter
public class WorkshopParticipantModel extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oficina_id")
    private WorkshopModel workshop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private StudentModel student;
}
