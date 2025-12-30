package br.ong.sementesamanha.erp.modules.education.domain.ports;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonDocument;
import java.util.List;

public interface PersonDocumentRepository {
    PersonDocument save(PersonDocument document);
    List<PersonDocument> findAllByPersonId(Long personId);
}