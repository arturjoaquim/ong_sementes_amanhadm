package br.ong.sementesamanha.erp.modules.education.domain.ports.repository;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;

public interface IndividualPersonRepository {
    IndividualPerson save(IndividualPerson person);
}
