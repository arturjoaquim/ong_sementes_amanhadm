package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.adapter;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.ports.IndividualPersonRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.IndividualPersonMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.IndividualPersonModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.IndividualPersonJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IndividualPersonJpaAdapter implements IndividualPersonRepository {

    private final IndividualPersonJpaRepository jpaRepository;
    private final IndividualPersonMapper mapper;

    public IndividualPersonJpaAdapter(IndividualPersonJpaRepository jpaRepository, IndividualPersonMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public IndividualPerson save(IndividualPerson person) {
        IndividualPersonModel model = mapper.toModel(person);
        IndividualPersonModel savedModel = jpaRepository.save(model);
        return mapper.toDomain(savedModel);
    }
}
