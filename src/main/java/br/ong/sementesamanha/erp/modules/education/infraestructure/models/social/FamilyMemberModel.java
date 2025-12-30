package br.ong.sementesamanha.erp.modules.education.infraestructure.models.social;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "membros_familia", schema = "social")
@Getter
@Setter
public class FamilyMemberModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familia_id")
    private FamilyModel family;

    @Column(name = "nome")
    private String name;

    @Column(name = "profissao")
    private String profession;

    @Column(name = "renda_mensal")
    private BigDecimal monthlyIncome;

    @Column(name = "parentesco_id")
    private Long kinshipId;

    @Column(name = "ativo")
    private boolean active;
}
