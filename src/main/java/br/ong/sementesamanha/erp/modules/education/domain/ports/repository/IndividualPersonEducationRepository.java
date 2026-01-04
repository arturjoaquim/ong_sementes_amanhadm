package br.ong.sementesamanha.erp.modules.education.domain.ports.repository;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPersonEducation;

public interface IndividualPersonEducationRepository {
    IndividualPersonEducation save(IndividualPersonEducation education);
}