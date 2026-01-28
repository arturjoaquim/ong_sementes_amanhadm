package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.domain.entities.HomeCondition;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.HomeConditionModel;
import org.springframework.stereotype.Component;

@Component
public class HomeConditionMapper {

    public HomeCondition toDomain(HomeConditionModel model) {
        if (model == null) return null;
        HomeCondition domain = new HomeCondition();
        domain.setId(model.getId());
        if (model.getFamily() != null) {
            domain.setFamilyId(model.getFamily().getId());
        }
        domain.setParentsMaritalStatusId(model.getParentsMaritalStatusId());
        domain.setContactWithSpouse(model.getKeepsContactWithSpouse());
        domain.setStaysAlone(model.getStaysAloneAtHome());
        return domain;
    }
}
