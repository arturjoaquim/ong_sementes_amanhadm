package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.PersonAddressDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Person;
import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonAddress;
import org.springframework.stereotype.Component;

@Component
public class PersonAddressMapper {

    public PersonAddress toDomain(PersonAddressDTO dto, Person person) {
        if (dto == null) return null;
        PersonAddress domain = new PersonAddress();
        domain.setPerson(person);
        domain.setCep(dto.cep());
        domain.setStreetNumber(dto.streetNumber());
        domain.setStreet(dto.street());
        domain.setNeighborhood(dto.neighborhood());
        domain.setCity(dto.city());
        domain.setComplement(dto.complement());
        domain.setUf(dto.uf());
        return domain;
    }

    public void updateDomainFromDto(PersonAddress domain, PersonAddressDTO dto) {
        if (dto == null || domain == null) return;
        
        if (dto.cep() != null) domain.setCep(dto.cep());
        if (dto.streetNumber() != null) domain.setStreetNumber(dto.streetNumber());
        if (dto.street() != null) domain.setStreet(dto.street());
        if (dto.neighborhood() != null) domain.setNeighborhood(dto.neighborhood());
        if (dto.city() != null) domain.setCity(dto.city());
        if (dto.complement() != null) domain.setComplement(dto.complement());
        if (dto.uf() != null) domain.setUf(dto.uf());
    }

    public PersonAddressDTO toDTO(PersonAddress domain) {
        if (domain == null) return null;
        return new PersonAddressDTO(
            domain.getCep(),
            domain.getStreetNumber(),
            domain.getStreet(),
            domain.getNeighborhood(),
            domain.getCity(),
            domain.getComplement(),
            domain.getUf()
        );
    }
}
