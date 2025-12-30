package br.ong.sementesamanha.erp.modules.education.infraestructure.models.social;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "riscos_familiares", schema = "social")
@Getter
@Setter
public class FamilyRiskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familia_id")
    private FamilyModel family;

    @Column(name = "descricao_risco")
    private String riskDescription;

    @Column(name = "is_prioridade")
    private boolean priority;
}
