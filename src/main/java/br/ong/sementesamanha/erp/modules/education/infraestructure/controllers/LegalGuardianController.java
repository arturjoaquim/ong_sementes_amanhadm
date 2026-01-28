package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateLegalGuardianDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.UpdateLegalGuardianDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.LegalGuardianService;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.LegalGuardian;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.IndividualPersonMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/guardians")
public class LegalGuardianController {

    private final LegalGuardianService service;
    private final IndividualPersonMapper personMapper;

    public LegalGuardianController(LegalGuardianService service, IndividualPersonMapper personMapper) {
        this.service = service;
        this.personMapper = personMapper;
    }

    @PostMapping
    public ResponseEntity<LegalGuardian> create(@RequestBody CreateLegalGuardianDTO dto) {
        IndividualPerson person = personMapper.toDomain(dto.person());
        LegalGuardian createdGuardian = service.create(person);
        return ResponseEntity.created(URI.create("/guardians/" + createdGuardian.getId())).body(createdGuardian);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateLegalGuardianDTO dto) {
        service.update(id, dto);
        return ResponseEntity.noContent().build();
    }
}
