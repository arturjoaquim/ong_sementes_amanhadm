package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.PersonDocumentDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonDocument;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.PersonDocumentMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.IndividualPersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonDocumentService {

    private final IndividualPersonRepository personRepository;
    private final PersonDocumentMapper documentMapper;

    public PersonDocumentService(IndividualPersonRepository personRepository, PersonDocumentMapper documentMapper) {
        this.personRepository = personRepository;
        this.documentMapper = documentMapper;
    }

    @Transactional(readOnly = true)
    public List<PersonDocumentDTO> getDocuments(Long personId) {
        IndividualPerson person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found with id: " + personId));

        if (person.getBasePerson().getDocuments() == null) {
            return Collections.emptyList();
        }

        return person.getBasePerson().getDocuments().stream()
                .map(documentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PersonDocumentDTO addDocument(Long personId, PersonDocumentDTO dto) {
        IndividualPerson person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found with id: " + personId));

        PersonDocument newDocument = documentMapper.toDomain(dto, person.getBasePerson());
        
        if (person.getBasePerson().getDocuments() == null) {
            person.getBasePerson().setDocuments(new ArrayList<>());
        }
        person.getBasePerson().getDocuments().add(newDocument);

        personRepository.save(person);
        return documentMapper.toDTO(newDocument);
    }

    @Transactional
    public PersonDocumentDTO updateDocument(Long personId, Long documentId, PersonDocumentDTO dto) {
        IndividualPerson person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found with id: " + personId));

        if (person.getBasePerson().getDocuments() == null) {
            throw new IllegalArgumentException("Document not found with id: " + documentId);
        }

        PersonDocument documentToUpdate = person.getBasePerson().getDocuments().stream()
                .filter(doc -> doc.getId() != null && doc.getId().equals(documentId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Document not found with id: " + documentId));

        documentMapper.updateDomainFromDto(documentToUpdate, dto);

        personRepository.save(person);
        return documentMapper.toDTO(documentToUpdate);
    }

    @Transactional
    public void removeDocument(Long personId, Long documentId) {
        IndividualPerson person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found with id: " + personId));

        if (person.getBasePerson().getDocuments() != null) {
            boolean removed = person.getBasePerson().getDocuments().removeIf(doc -> doc.getId() != null && doc.getId().equals(documentId));
            if (removed) {
                personRepository.save(person);
            } else {
                throw new IllegalArgumentException("Document not found with id: " + documentId);
            }
        }
    }
}
