package br.ong.sementesamanha.erp.modules.education.domain.ports;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;

public interface IndividualPersonServicePort {
    IndividualPerson create(IndividualPerson person);
}
