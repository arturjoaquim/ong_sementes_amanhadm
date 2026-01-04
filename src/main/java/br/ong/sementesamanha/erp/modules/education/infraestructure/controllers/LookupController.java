package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.LookupDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.LookupService;
import br.ong.sementesamanha.erp.modules.education.infraestructure.enums.LookupTypeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lookups")
public class LookupController {

    private final LookupService service;

    public LookupController(LookupService service) {
        this.service = service;
    }

    @GetMapping("/{type}/map")
    public ResponseEntity<Map<Long, LookupDTO>> getLookupAsMap(@PathVariable String type) {
        try {
            LookupTypeEnum lookupTypeEnum = LookupTypeEnum.valueOf(type.toUpperCase());
            Map<Long, LookupDTO> data = service.getLookupAsMap(lookupTypeEnum);
            return ResponseEntity.ok(data);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{type}/list")
    public ResponseEntity<List<LookupDTO>> getLookupAsList(@PathVariable String type) {
        try {
            LookupTypeEnum lookupTypeEnum = LookupTypeEnum.valueOf(type.toUpperCase());
            List<LookupDTO> data = service.getLookupAsList(lookupTypeEnum);
            return ResponseEntity.ok(data);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
