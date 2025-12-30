package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.adapter;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonAddress;
import br.ong.sementesamanha.erp.modules.education.domain.ports.PersonAddressRepositoryPort;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.PersonAddressMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.PersonAddressModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.PersonAddressJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PersonAddressJpaAdapter implements PersonAddressRepositoryPort {

    private final PersonAddressJpaRepository jpaRepository;
    private final PersonAddressMapper mapper;

    public PersonAddressJpaAdapter(PersonAddressJpaRepository jpaRepository, PersonAddressMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PersonAddress save(PersonAddress address) {
        PersonAddressModel model = mapper.toModel(address);
        PersonAddressModel savedModel = jpaRepository.save(model);
        return mapper.toDomain(savedModel);
    }
}
