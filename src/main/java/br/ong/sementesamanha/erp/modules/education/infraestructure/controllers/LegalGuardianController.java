package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.guardian.CreateLegalGuardianDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.guardian.LegalGuardianResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.guardian.UpdateLegalGuardianDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.LegalGuardianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/guardians")
public class LegalGuardianController {

    private final LegalGuardianService service;

    public LegalGuardianController(LegalGuardianService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LegalGuardianResponseDTO> create(@RequestBody CreateLegalGuardianDTO dto) {
        LegalGuardianResponseDTO createdGuardian = service.create(dto);
        return ResponseEntity.created(URI.create("/guardians/" + createdGuardian.id())).body(createdGuardian);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateLegalGuardianDTO dto) {
        service.update(id, dto);
        return ResponseEntity.noContent().build();
    }
}
