package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.guardian.LegalGuardianResponseDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.LegalGuardian;
import org.springframework.stereotype.Component;

@Component
public class LegalGuardianMapper {

    private final IndividualPersonMapper individualPersonMapper;

    public LegalGuardianMapper(IndividualPersonMapper individualPersonMapper) {
        this.individualPersonMapper = individualPersonMapper;
    }

    public LegalGuardianResponseDTO toDTO(LegalGuardian entity) {
        if (entity == null) return null;
        return new LegalGuardianResponseDTO(
            entity.getId(),
            individualPersonMapper.toDTO(entity.getPerson())
        );
    }
}
