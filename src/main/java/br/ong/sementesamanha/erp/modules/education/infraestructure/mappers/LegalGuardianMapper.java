package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.domain.entities.LegalGuardian;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.LegalGuardianModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.IndividualPersonModel;
import org.springframework.stereotype.Component;

@Component
public class LegalGuardianMapper {

    private IndividualPersonMapper individualPersonMapper;

    public LegalGuardianMapper(IndividualPersonMapper individualPersonMapper) {
        this.individualPersonMapper = individualPersonMapper;
    }

    public LegalGuardian toDomain(LegalGuardianModel model) {
        if (model == null) return null;
        LegalGuardian domain = new LegalGuardian();
        domain.setId(model.getId());
        if (model.getPerson() != null) {
            domain.setPersonId(model.getPerson().getId());
        }
        return domain;
    }

    public LegalGuardianModel toModel(LegalGuardian domain) {
        if (domain == null) return null;
        LegalGuardianModel model = new LegalGuardianModel();
        updateModelFromDomain(model, domain);
        return model;
    }

    public void updateModelFromDomain(LegalGuardianModel model, LegalGuardian domain) {
        if (model == null || domain == null) return;

        model.setId(domain.getId());
        if (domain.getPersonId() != null) {
            if (model.getPerson() == null) {
                IndividualPersonModel person = new IndividualPersonModel();
                person.setId(domain.getPersonId());
                model.setPerson(person);
            }
        }
    }
}
