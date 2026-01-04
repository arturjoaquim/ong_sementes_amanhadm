package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.adapter;

import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonDocument;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.PersonDocumentRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.PersonDocumentMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.PersonDocumentModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.PersonDocumentJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDocumentJpaAdapter implements PersonDocumentRepository {

    private final PersonDocumentJpaRepository jpaRepository;
    private final PersonDocumentMapper mapper;

    public PersonDocumentJpaAdapter(PersonDocumentJpaRepository jpaRepository, PersonDocumentMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PersonDocument save(PersonDocument document) {
        PersonDocumentModel model = mapper.toModel(document);
        PersonDocumentModel savedModel = jpaRepository.save(model);
        return mapper.toDomain(savedModel);
    }
}
