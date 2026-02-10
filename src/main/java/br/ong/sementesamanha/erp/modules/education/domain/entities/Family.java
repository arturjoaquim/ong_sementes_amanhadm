package br.ong.sementesamanha.erp.modules.education.domain.entities;

import br.ong.sementesamanha.erp.modules.education.domain.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "familias", schema = "social")
@Getter
@Setter
public class Family extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_domicilio_id")
    private Long domicileTypeId;

    @Column(name = "avaliacao_familiar")
    private String familyAssessment;

    @Column(name = "data_vencimento_info")
    private LocalDate infoExpirationDate;

    @Column(name = "cras_referencia")
    private String referenceCras;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<FamilyMember> members;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<FamilyBenefit> benefits;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<FamilyRisk> risks;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<HomeVisit> homeVisits;
}
