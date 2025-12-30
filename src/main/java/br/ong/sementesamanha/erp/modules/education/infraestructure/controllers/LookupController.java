package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.LookupDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.LookupService;
import br.ong.sementesamanha.erp.modules.education.domain.types.LookupType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lookups")
public class LookupController {

    private final LookupService service;

    public LookupController(LookupService service) {
        this.service = service;
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<LookupDTO>> getLookup(@PathVariable String type) {
        try {
            // Converte a string da URL (ex: "GENDER" ou "gender") para o Enum
            LookupType lookupType = LookupType.valueOf(type.toUpperCase());
            List<LookupDTO> data = service.getLookup(lookupType);
            return ResponseEntity.ok(data);
        } catch (IllegalArgumentException e) {
            // Retorna 400 se o tipo não existir no Enum
            return ResponseEntity.badRequest().build();
        }
    }
}
