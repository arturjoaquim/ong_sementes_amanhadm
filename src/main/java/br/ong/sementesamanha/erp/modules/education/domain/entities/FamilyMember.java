package br.ong.sementesamanha.erp.modules.education.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "membros_familia", schema = "social")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familia_id")
    private Family family;

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

    @CreatedDate
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "criado_por_id", updatable = false)
    private Long createdBy;
}
