package br.ong.sementesamanha.erp.modules.education.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "familias_beneficios", schema = "social")
@Getter
@Setter
public class FamilyBenefit {

    @EmbeddedId
    private FamilyBenefitId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("familyId")
    @JoinColumn(name = "familia_id")
    private Family family;

}
