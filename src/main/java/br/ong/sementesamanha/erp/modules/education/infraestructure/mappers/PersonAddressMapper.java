package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateAddressDTO;
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
        return domain;
    }

    public PersonAddress toDomain(CreateAddressDTO dto) {
        PersonAddress address = new PersonAddress();
        address.setCep(dto.cep());
        address.setStreetNumber(dto.streetNumber());
        address.setStreet(dto.street());
        address.setNeighborhood(dto.neighborhood());
        address.setCity(dto.city());
        address.setComplement(dto.complement());
        address.setUf(dto.uf());
        return address;
    }

    public PersonAddressModel toModel(PersonAddress domain) {
        if (domain == null) return null;
        PersonAddressModel model = new PersonAddressModel();
        model.setId(domain.getId());
        model.setCep(domain.getCep());
        model.setStreetNumber(domain.getStreetNumber());
        model.setStreet(domain.getStreet());
        model.setNeighborhood(domain.getNeighborhood());
        model.setCity(domain.getCity());
        model.setComplement(domain.getComplement());
        return model;
    }
}
