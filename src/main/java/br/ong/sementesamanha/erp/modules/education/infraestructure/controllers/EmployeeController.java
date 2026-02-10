package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.CreateEmployeeDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.UpdateEmployeeDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.EmployeeService;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody CreateEmployeeDTO dto) {
        Employee createdEmployee = service.create(dto);
        return ResponseEntity.created(URI.create("/employees/" + createdEmployee.getId())).body(createdEmployee);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateEmployeeDTO dto) {
        service.update(id, dto);
        return ResponseEntity.noContent().build();
    }
}
