package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.IndividualPersonDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Person;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.IndividualPersonMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.IndividualPersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndividualPersonService {

    private final IndividualPersonRepository repository;
    private final IndividualPersonMapper mapper;

    public IndividualPersonService(IndividualPersonRepository repository, IndividualPersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public IndividualPerson create(IndividualPerson person) {
        if (person.getBasePerson() == null) {
            Person basePerson = new Person();
            basePerson.setPersonType(1);
            person.setBasePerson(basePerson);
        }
        return repository.save(person);
    }

    @Transactional
    public IndividualPerson update(Long id, IndividualPersonDTO dto) {
        IndividualPerson existingPerson = repository.findByIdForceLoad(id)
                .orElseThrow(() -> new IllegalArgumentException("Person not found with id: " + id));

        mapper.updateDomainFromDto(existingPerson, dto);
        return repository.save(existingPerson);
    }
}
