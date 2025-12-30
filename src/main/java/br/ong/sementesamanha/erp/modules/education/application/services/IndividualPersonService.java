package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.ports.IndividualPersonRepository;
import br.ong.sementesamanha.erp.modules.education.domain.ports.IndividualPersonServicePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndividualPersonService implements IndividualPersonServicePort {

    private final IndividualPersonRepository repository;

    public IndividualPersonService(IndividualPersonRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public IndividualPerson create(IndividualPerson person) {
        // Validações de domínio da pessoa aqui (CPF, idade, etc)
        return repository.save(person);
    }
}
