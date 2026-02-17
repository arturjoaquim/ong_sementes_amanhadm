package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.CreateEmployeeDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.EmployeePreviewResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.EmployeeResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.UpdateEmployeeDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.EmployeeService;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Employee;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.EmployeeMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeeMapper mapper;

    public EmployeeController(EmployeeService service, EmployeeMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/preview")
    public ResponseEntity<List<EmployeePreviewResponseDTO>> getAllPreviews() {
        return ResponseEntity.ok(service.getAllPreviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getById(@PathVariable Long id) {
        Employee employee = service.findById(id);
        return ResponseEntity.ok(mapper.toDTO(employee));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<EmployeeResponseDTO> getByUserId(@PathVariable Long userId) {
        Employee employee = service.findByUserId(userId);
        return ResponseEntity.ok(mapper.toDTO(employee));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> create(@RequestBody CreateEmployeeDTO dto) {
        Employee created = service.create(dto);
        return ResponseEntity.created(URI.create("/employees/" + created.getId())).body(mapper.toDTO(created));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> update(@PathVariable Long id, @RequestBody UpdateEmployeeDTO dto) {
        Employee updated = service.update(id, dto);
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
