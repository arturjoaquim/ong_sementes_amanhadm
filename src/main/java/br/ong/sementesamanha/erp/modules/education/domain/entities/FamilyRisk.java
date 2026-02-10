package br.ong.sementesamanha.erp.modules.education.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "riscos_familiares", schema = "social")
@Getter
@Setter
public class FamilyRisk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familia_id")
    private Family family;

    @Column(name = "descricao_risco")
    private String description;

    @Column(name = "is_prioridade")
    private Boolean isPriority;
}
