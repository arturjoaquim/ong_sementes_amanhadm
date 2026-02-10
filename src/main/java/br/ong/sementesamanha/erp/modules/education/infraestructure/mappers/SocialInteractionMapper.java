package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.SocialInteractionResponseDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.SocialInteraction;
import org.springframework.stereotype.Component;

@Component
public class SocialInteractionMapper {

    public SocialInteractionResponseDTO toDTO(SocialInteraction entity) {
        if (entity == null) return null;
        return new SocialInteractionResponseDTO(
            entity.getId(),
            entity.getInteractionLevel(),
            entity.getSocialGroupDescription()
        );
    }
}
