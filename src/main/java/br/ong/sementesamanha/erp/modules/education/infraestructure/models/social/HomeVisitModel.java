package br.ong.sementesamanha.erp.modules.education.infraestructure.models.social;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitas_domiciliares", schema = "social")
@Getter
@Setter
public class HomeVisitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familia_id")
    private FamilyModel family;

    @Column(name = "data_visita")
    private LocalDateTime visitDate;

    @Column(name = "resumo")
    private String summary;

    @Column(name = "relatorio_completo")
    private String fullReport;
}
