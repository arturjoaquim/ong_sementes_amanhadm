package br.ong.sementesamanha.erp.modules.education.domain.ports;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonAddress;

public interface PersonAddressRepositoryPort {
    PersonAddress save(PersonAddress address);
    // Outros métodos...
}
