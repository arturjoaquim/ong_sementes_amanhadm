package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.PersonContactDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Person;
import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonContact;
import org.springframework.stereotype.Component;

@Component
public class PersonContactMapper {

    public PersonContact toDomain(PersonContactDTO dto, Person person) {
        if (dto == null) return null;
        PersonContact domain = new PersonContact();
        domain.setPerson(person);
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

    public PersonContactDTO toDTO(PersonContact domain) {
        if (domain == null) return null;
        return new PersonContactDTO(
            domain.getTelephone(),
            domain.getMobilePhone(),
            domain.isHasWhatsApp(),
            domain.getEmail()
        );
    }
}
