package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.PersonAddressDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonAddress;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.PersonAddressModel;
import org.springframework.stereotype.Component;

@Component
public class PersonAddressMapper {

    public PersonAddress toDomain(PersonAddressModel model) {
        if (model == null) return null;
        PersonAddress domain = new PersonAddress();
        domain.setId(model.getId());
        domain.setCep(model.getCep());
        domain.setStreetNumber(model.getStreetNumber());
        domain.setStreet(model.getStreet());
        domain.setNeighborhood(model.getNeighborhood());
        domain.setCity(model.getCity());
        domain.setComplement(model.getComplement());
        domain.setUf(model.getUf());
        return domain;
    }

    public PersonAddressModel toModel(PersonAddress domain) {
        if (domain == null) return null;
        PersonAddressModel model = new PersonAddressModel();
        updateModelFromDomain(model, domain);
        return model;
    }

    public void updateModelFromDomain(PersonAddressModel model, PersonAddress domain) {
        if (domain == null || model == null) return;
        model.setId(domain.getId());
        model.setCep(domain.getCep());
        model.setStreetNumber(domain.getStreetNumber());
        model.setStreet(domain.getStreet());
        model.setNeighborhood(domain.getNeighborhood());
        model.setCity(domain.getCity());
        model.setComplement(domain.getComplement());
        model.setUf(domain.getUf());
    }

    public PersonAddress toDomain(PersonAddressDTO dto) {
        if (dto == null) return null;
        PersonAddress domain = new PersonAddress();
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
}
