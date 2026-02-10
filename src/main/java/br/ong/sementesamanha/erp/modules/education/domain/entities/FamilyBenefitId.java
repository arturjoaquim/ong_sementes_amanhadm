package br.ong.sementesamanha.erp.modules.education.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyBenefitId implements Serializable {
    @Column(name = "familia_id")
    private Long familyId;

    @Column(name = "beneficio_id")
    private Long benefitId;
}
