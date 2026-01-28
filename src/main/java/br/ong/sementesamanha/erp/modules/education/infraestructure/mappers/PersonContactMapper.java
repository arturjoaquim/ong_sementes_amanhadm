package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.PersonContactDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonContact;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.PersonContactModel;
import org.springframework.stereotype.Component;

@Component
public class PersonContactMapper {

    public PersonContact toDomain(PersonContactModel model) {
        if (model == null) return null;
        PersonContact domain = new PersonContact();
        domain.setId(model.getId());
        domain.setTelephone(model.getTelephone());
        domain.setMobilePhone(model.getMobilePhone());
        domain.setHasWhatsApp(model.isHasWhatsApp());
        domain.setEmail(model.getEmail());
        return domain;
    }

    public PersonContactModel toModel(PersonContact domain) {
        if (domain == null) return null;
        PersonContactModel model = new PersonContactModel();
        updateModelFromDomain(model, domain);
        return model;
    }

    public void updateModelFromDomain(PersonContactModel model, PersonContact domain) {
        if (domain == null || model == null) return;
        model.setId(domain.getId());
        model.setTelephone(domain.getTelephone());
        model.setMobilePhone(domain.getMobilePhone());
        model.setHasWhatsApp(domain.isHasWhatsApp());
        model.setEmail(domain.getEmail());
    }

    public PersonContact toDomain(PersonContactDTO dto) {
        if (dto == null) return null;
        PersonContact domain = new PersonContact();
        domain.setTelephone(dto.telephone());
        domain.setMobilePhone(dto.mobilePhone());
        if (dto.hasWhatsApp() != null) domain.setHasWhatsApp(dto.hasWhatsApp());
        domain.setEmail(dto.email());
        return domain;
    }

    public void updateDomainFromDto(PersonContact domain, PersonContactDTO dto) {
        if (dto == null || domain == null) return;

        if (dto.telephone() != null) domain.setTelephone(dto.telephone());
        if (dto.mobilePhone() != null) domain.setMobilePhone(dto.mobilePhone());
        if (dto.hasWhatsApp() != null) domain.setHasWhatsApp(dto.hasWhatsApp());
        if (dto.email() != null) domain.setEmail(dto.email());
    }
}
