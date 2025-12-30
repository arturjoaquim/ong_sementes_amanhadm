package br.ong.sementesamanha.erp.modules.education.domain.ports;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPersonEducation;
import java.util.Optional;

public interface IndividualPersonEducationRepository {
    IndividualPersonEducation save(IndividualPersonEducation education);
    Optional<IndividualPersonEducation> findByPersonId(Long personId);
}