package br.ong.sementesamanha.erp.modules.education.domain.ports;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPersonEducation;

public interface IndividualPersonEducationRepositoryPort {
    IndividualPersonEducation save(IndividualPersonEducation education);
}
