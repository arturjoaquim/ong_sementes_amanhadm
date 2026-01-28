package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.domain.entities.SocialInteraction;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.SocialInteractionModel;
import org.springframework.stereotype.Component;

@Component
public class SocialInteractionMapper {

    public SocialInteraction toDomain(SocialInteractionModel model) {
        if (model == null) return null;
        SocialInteraction domain = new SocialInteraction();
        domain.setId(model.getId());
        domain.setInteractionLevel(model.getInteractionLevel());
        domain.setSocialGroupDescription(model.getSocialGroupDescription());
        return domain;
    }
}
