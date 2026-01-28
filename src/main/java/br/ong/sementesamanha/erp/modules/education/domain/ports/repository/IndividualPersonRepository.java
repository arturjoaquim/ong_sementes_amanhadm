package br.ong.sementesamanha.erp.modules.education.domain.ports.repository;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;

import java.util.Optional;

public interface IndividualPersonRepository {
    IndividualPerson save(IndividualPerson person);

    Optional<IndividualPerson> findById(Long id);
}
