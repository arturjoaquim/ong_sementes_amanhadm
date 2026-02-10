package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.social.FamilyBenefitResponseDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.FamilyBenefit;
import org.springframework.stereotype.Component;

@Component
public class FamilyBenefitMapper {

    public FamilyBenefitResponseDTO toDTO(FamilyBenefit entity) {
        if (entity == null || entity.getId() == null) return null;
        return new FamilyBenefitResponseDTO(
            entity.getId().getBenefitId()
        );
    }
}
