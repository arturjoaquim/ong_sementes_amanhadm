package br.ong.sementesamanha.erp.modules.education.domain.ports.repository;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonAddress;

public interface PersonAddressRepository {
    PersonAddress save(PersonAddress address);
}