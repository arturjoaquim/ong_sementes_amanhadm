package br.ong.sementesamanha.erp.modules.education.domain.entities;

import br.ong.sementesamanha.erp.modules.education.domain.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "oficina_presencas", schema = "academico")
@Getter
@Setter
public class WorkshopPresence extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_oficina_id")
    private WorkshopSession workshopSession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_id")
    private WorkshopEnrollment workshopEnrollment;
}
