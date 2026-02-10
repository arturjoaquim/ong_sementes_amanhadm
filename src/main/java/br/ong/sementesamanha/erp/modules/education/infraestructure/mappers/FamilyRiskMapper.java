package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.social.FamilyRiskResponseDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.FamilyRisk;
import org.springframework.stereotype.Component;

@Component
public class FamilyRiskMapper {

    public FamilyRiskResponseDTO toDTO(FamilyRisk entity) {
        if (entity == null) return null;
        return new FamilyRiskResponseDTO(
            entity.getId(),
            entity.getDescription(),
            entity.getIsPriority()
        );
    }
}
