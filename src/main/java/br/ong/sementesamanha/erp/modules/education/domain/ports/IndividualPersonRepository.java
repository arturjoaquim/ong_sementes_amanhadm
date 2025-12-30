package br.ong.sementesamanha.erp.modules.education.domain.ports;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;

public interface IndividualPersonRepository {
    IndividualPerson save(IndividualPerson person);
    // Outros métodos...
}
