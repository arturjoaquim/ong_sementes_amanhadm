package br.ong.sementesamanha.erp.modules.education.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "visitas_domiciliares", schema = "social")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class HomeVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familia_id")
    private Family family;

    @Column(name = "data_visita")
    private LocalDateTime visitDate;

    @Column(name = "resumo")
    private String summary;

    @Column(name = "relatorio_completo")
    private String fullReport;

    @CreatedBy
    @Column(name = "criado_por_id", updatable = false)
    private Long createdBy;

    @CreatedDate
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
