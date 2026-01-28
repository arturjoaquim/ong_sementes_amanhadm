package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateWorkshopDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.WorkshopService;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Workshop;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/workshops")
public class WorkshopController {

    private final WorkshopService service;

    public WorkshopController(WorkshopService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Workshop> create(@RequestBody CreateWorkshopDTO dto) {
        Workshop createdWorkshop = service.create(dto);
        return ResponseEntity.created(URI.create("/workshops/" + createdWorkshop.getId())).body(createdWorkshop);
    }
}
