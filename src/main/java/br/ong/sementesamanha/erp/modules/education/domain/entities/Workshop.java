package br.ong.sementesamanha.erp.modules.education.domain.entities;

import br.ong.sementesamanha.erp.modules.education.domain.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "oficinas", schema = "academico")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Workshop extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_oficina")
    private String name;

    @Column(name = "maximo_participantes")
    private Integer enrollmentLimit;

    @Column(name = "ativo")
    private Boolean active;

    @OneToMany(mappedBy = "workshop", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WorkshopSession> sessions;

    @OneToMany(mappedBy = "workshop", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WorkshopEnrollment> enrollments;
}
