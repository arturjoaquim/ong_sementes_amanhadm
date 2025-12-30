package br.ong.sementesamanha.erp.modules.education.domain.ports;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonAddress;
import java.util.Optional;

public interface PersonAddressRepository {
    PersonAddress save(PersonAddress address);
    Optional<PersonAddress> findByPersonId(Long personId);
}