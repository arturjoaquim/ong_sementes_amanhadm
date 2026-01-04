package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.adapter;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonContact;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.PersonContactRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.PersonContactMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.PersonContactModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.PersonContactJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PersonContactJpaAdapter implements PersonContactRepository {

    private final PersonContactJpaRepository jpaRepository;
    private final PersonContactMapper mapper;

    public PersonContactJpaAdapter(PersonContactJpaRepository jpaRepository, PersonContactMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PersonContact save(PersonContact contact) {
        PersonContactModel model = mapper.toModel(contact);
        PersonContactModel savedModel = jpaRepository.save(model);
        return mapper.toDomain(savedModel);
    }
}
