package br.ong.sementesamanha.erp.modules.education.domain.ports;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonContact;

public interface PersonContactRepositoryPort {
    PersonContact save(PersonContact contact);
}
