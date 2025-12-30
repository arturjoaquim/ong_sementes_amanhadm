package br.ong.sementesamanha.erp.modules.education.infraestructure.models.social;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "familias", schema = "social")
@Getter
@Setter
public class FamilyModel {
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
    private List<FamilyMemberModel> members;
}
