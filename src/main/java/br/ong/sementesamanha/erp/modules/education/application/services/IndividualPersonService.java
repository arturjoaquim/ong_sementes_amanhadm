package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.IndividualPersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndividualPersonService {

    private final IndividualPersonRepository repository;

    public IndividualPersonService(IndividualPersonRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public IndividualPerson create(IndividualPerson person) {
        // A lógica de criação da Person base foi movida para o JPA via Cascade e @MapsId
        return repository.save(person);
    }
}
