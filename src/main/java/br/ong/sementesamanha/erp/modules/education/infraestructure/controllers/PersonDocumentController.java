package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.PersonDocumentDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.PersonDocumentService;
import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonDocument;
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
    public ResponseEntity<PersonDocument> addDocument(@PathVariable Long personId, @RequestBody PersonDocumentDTO dto) {
        PersonDocument createdDocument = service.addDocument(personId, dto);
        return ResponseEntity.created(URI.create("/people/" + personId + "/documents")).body(createdDocument);
    }

    @PatchMapping("/{documentId}")
    public ResponseEntity<PersonDocument> updateDocument(@PathVariable Long personId, @PathVariable Long documentId, @RequestBody PersonDocumentDTO dto) {
        PersonDocument updatedDocument = service.updateDocument(personId, documentId, dto);
        return ResponseEntity.ok(updatedDocument);
    }

    @PatchMapping("/{documentId}/inactivate")
    public ResponseEntity<Void> inactivateDocument(@PathVariable Long personId, @PathVariable Long documentId) {
        service.inactivateDocument(personId, documentId);
        return ResponseEntity.noContent().build();
    }
}
