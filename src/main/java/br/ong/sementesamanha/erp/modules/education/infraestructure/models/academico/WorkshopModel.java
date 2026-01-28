package br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.rh.EmployeeModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "oficinas", schema = "academico")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class WorkshopModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_oficina_id")
    private Long workshopTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "educador_responsavel_id")
    private EmployeeModel responsibleEducator;

    @Column(name = "link_lista_presenca")
    private String attendanceListLink;

    @Column(name = "descricao")
    private String description;

    @OneToMany(mappedBy = "workshop", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WorkshopParticipantModel> participants;

    @CreatedDate
    @Column(name="criado_em")
    private LocalDateTime createdDate;

    @CreatedBy
    @Column(name="criado_por_id")
    private Long createdByUser;
}
