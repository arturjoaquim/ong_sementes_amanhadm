package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateContactDTO;
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

    public PersonContact toDomain(CreateContactDTO dto) {
        PersonContact contact = new PersonContact();
        contact.setTelephone(dto.telephone());
        contact.setMobilePhone(dto.mobilePhone());
        contact.setHasWhatsApp(dto.hasWhatsApp());
        contact.setEmail(dto.email());
        return contact;
    }

    public PersonContactModel toModel(PersonContact domain) {
        if (domain == null) return null;
        PersonContactModel model = new PersonContactModel();
        model.setId(domain.getId());
        model.setTelephone(domain.getTelephone());
        model.setMobilePhone(domain.getMobilePhone());
        model.setHasWhatsApp(domain.isHasWhatsApp());
        model.setEmail(domain.getEmail());
        return model;
    }
}
