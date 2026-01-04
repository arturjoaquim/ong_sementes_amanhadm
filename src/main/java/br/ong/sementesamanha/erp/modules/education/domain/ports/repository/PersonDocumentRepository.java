package br.ong.sementesamanha.erp.modules.education.domain.ports.repository;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonDocument;

public interface PersonDocumentRepository {
    PersonDocument save(PersonDocument document);
}