package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.CreateWorkshopDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.CreateWorkshopSessionDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopDetailsResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopPreviewResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopSessionResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.WorkshopService;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Workshop;
import br.ong.sementesamanha.erp.modules.education.domain.entities.WorkshopSession;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.WorkshopMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/workshops")
public class WorkshopController {

    private final WorkshopService service;
    private final WorkshopMapper mapper;

    public WorkshopController(WorkshopService service, WorkshopMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/preview")
    public ResponseEntity<List<WorkshopPreviewResponseDTO>> getAllPreviews() {
        return ResponseEntity.ok(service.getAllPreviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkshopDetailsResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<WorkshopDetailsResponseDTO> create(@RequestBody CreateWorkshopDTO workshop) {
        Workshop created = service.create(workshop);
        return ResponseEntity.created(URI.create("/workshops/" + created.getId())).body(mapper.toDetails(created));
    }

    @PostMapping("/sessions")
    public ResponseEntity<WorkshopSessionResponseDTO> createSession(@RequestBody CreateWorkshopSessionDTO session) {
        WorkshopSession created = service.createSession(session);
        return ResponseEntity.created(URI.create("/workshops/sessions/" + created.getId())).body(mapper.toSessionResponseDTO(created));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WorkshopDetailsResponseDTO> update(@PathVariable Long id, @RequestBody Workshop workshop) {
        Workshop updated = service.update(id, workshop);
        return ResponseEntity.ok(mapper.toDetails(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
