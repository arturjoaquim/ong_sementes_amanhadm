package br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.rh.EmployeeModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "oficinas", schema = "academico")
@Getter
@Setter
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
}
