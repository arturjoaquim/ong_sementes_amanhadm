package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.adapter;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.IndividualPersonRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.IndividualPersonMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.IndividualPersonModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.PersonModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.IndividualPersonJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IndividualPersonJpaAdapter implements IndividualPersonRepository {

    private final IndividualPersonJpaRepository jpaRepository;
    private final IndividualPersonMapper mapper;

    public IndividualPersonJpaAdapter(IndividualPersonJpaRepository jpaRepository, IndividualPersonMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public IndividualPerson save(IndividualPerson person) {
        IndividualPersonModel model = Optional.ofNullable(person.getId())
                .flatMap(jpaRepository::findById)
                .orElseGet(this::createNewModel);
        mapper.updateModelFromDomain(model, person);

        return mapper.toDomain(jpaRepository.save(model));
    }

    @Override
    public Optional<IndividualPerson> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    private IndividualPersonModel createNewModel() {
        IndividualPersonModel model = new IndividualPersonModel();

        PersonModel personModel = new PersonModel();
        personModel.setPersonType(1);

        model.setPerson(personModel);
        return model;
    }
}
