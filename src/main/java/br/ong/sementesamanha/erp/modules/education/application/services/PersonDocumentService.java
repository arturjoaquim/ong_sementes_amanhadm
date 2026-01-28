package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.PersonDocumentDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonDocument;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.IndividualPersonRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.PersonDocumentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonDocumentService {

    private final IndividualPersonRepository personRepository;
    private final PersonDocumentMapper documentMapper;

    public PersonDocumentService(IndividualPersonRepository personRepository, PersonDocumentMapper documentMapper) {
        this.personRepository = personRepository;
        this.documentMapper = documentMapper;
    }

    @Transactional
    public PersonDocument addDocument(Long personId, PersonDocumentDTO dto) {
        IndividualPerson person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found with id: " + personId));

        PersonDocument newDocument = documentMapper.toDomain(dto);
        
        if (person.getDocuments() == null) {
            person.setDocuments(new ArrayList<>());
        }
        person.getDocuments().add(newDocument);

        personRepository.save(person);
        return newDocument;
    }

    @Transactional
    public PersonDocument updateDocument(Long personId, Long documentId, PersonDocumentDTO dto) {
        IndividualPerson person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found with id: " + personId));

        if (person.getDocuments() == null) {
            throw new IllegalArgumentException("Document not found with id: " + documentId);
        }

        PersonDocument documentToUpdate = person.getDocuments().stream()
                .filter(doc -> doc.getId() != null && doc.getId().equals(documentId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Document not found with id: " + documentId));

        documentMapper.updateDomainFromDto(documentToUpdate, dto);

        personRepository.save(person);
        return documentToUpdate;
    }

    @Transactional
    public void inactivateDocument(Long personId, Long documentId) {
        IndividualPerson person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found with id: " + personId));

        if (person.getDocuments() != null) {
            Optional<PersonDocument> document = person.getDocuments().stream()
                    .filter(doc -> doc.getId() != null && doc.getId().equals(documentId))
                    .findFirst();

            document.ifPresentOrElse(doc -> doc.setActive(false), () -> {
                throw new IllegalArgumentException("Document not found with id: " + documentId);
            });
            personRepository.save(person);
        }
    }
}
