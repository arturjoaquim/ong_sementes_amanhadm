package br.ong.sementesamanha.erp.modules.education.domain.ports;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonDocument;

public interface PersonDocumentRepositoryPort {
    PersonDocument save(PersonDocument document);
}
