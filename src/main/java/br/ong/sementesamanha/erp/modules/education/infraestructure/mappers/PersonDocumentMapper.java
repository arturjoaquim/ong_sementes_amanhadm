package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonDocument;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.PersonDocumentModel;
import org.springframework.stereotype.Component;

@Component
public class PersonDocumentMapper {

    public PersonDocument toDomain(PersonDocumentModel model) {
        if (model == null) return null;
        PersonDocument domain = new PersonDocument();
        domain.setId(model.getId());
        domain.setDocumentTypeId(model.getDocumentTypeId());
        domain.setDocumentValue(model.getNumber());
        // DocumentDetail mapping omitted
        return domain;
    }

    public PersonDocumentModel toModel(PersonDocument domain) {
        if (domain == null) return null;
        PersonDocumentModel model = new PersonDocumentModel();
        model.setId(domain.getId());
        model.setDocumentTypeId(domain.getDocumentTypeId());
        model.setNumber(domain.getDocumentValue());
        return model;
    }
}
