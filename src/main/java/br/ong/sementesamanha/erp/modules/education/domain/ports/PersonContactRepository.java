package br.ong.sementesamanha.erp.modules.education.domain.ports;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonContact;
import java.util.Optional;

public interface PersonContactRepository {
    PersonContact save(PersonContact contact);
    Optional<PersonContact> findByPersonId(Long personId);
}