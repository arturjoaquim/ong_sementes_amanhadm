package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.PersonDocumentDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.PersonDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/people/{personId}/documents")
public class PersonDocumentController {

    private final PersonDocumentService service;

    public PersonDocumentController(PersonDocumentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PersonDocumentDTO> addDocument(@PathVariable Long personId, @RequestBody PersonDocumentDTO dto) {
        PersonDocumentDTO createdDocument = service.addDocument(personId, dto);
        return ResponseEntity.created(URI.create("/people/" + personId + "/documents/" + createdDocument.id())).body(createdDocument);
    }

    @PatchMapping("/{documentId}")
    public ResponseEntity<PersonDocumentDTO> updateDocument(@PathVariable Long personId, @PathVariable Long documentId, @RequestBody PersonDocumentDTO dto) {
        PersonDocumentDTO updatedDocument = service.updateDocument(personId, documentId, dto);
        return ResponseEntity.ok(updatedDocument);
    }

    @PatchMapping("/{documentId}/inactivate")
    public ResponseEntity<Void> inactivateDocument(@PathVariable Long personId, @PathVariable Long documentId) {
        service.removeDocument(personId, documentId);
        return ResponseEntity.noContent().build();
    }
}
