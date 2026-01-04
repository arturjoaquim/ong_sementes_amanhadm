package br.ong.sementesamanha.erp.modules.education.domain.ports.repository;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonContact;

public interface PersonContactRepository {
    PersonContact save(PersonContact contact);
}
