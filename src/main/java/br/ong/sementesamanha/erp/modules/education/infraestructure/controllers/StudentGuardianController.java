package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.CreateStudentGuardianDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.UpdateStudentGuardianDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.StudentGuardianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students/{studentId}/guardians")
public class StudentGuardianController {

    private final StudentGuardianService service;

    public StudentGuardianController(StudentGuardianService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> addGuardian(@PathVariable Long studentId, @RequestBody CreateStudentGuardianDTO dto) {
        service.addGuardian(studentId, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{guardianId}")
    public ResponseEntity<Void> updateGuardian(@PathVariable Long studentId, @PathVariable Long guardianId, @RequestBody UpdateStudentGuardianDTO dto) {
        service.updateGuardian(studentId, guardianId, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{guardianId}")
    public ResponseEntity<Void> removeGuardian(@PathVariable Long studentId, @PathVariable Long guardianId) {
        service.removeGuardian(studentId, guardianId);
        return ResponseEntity.noContent().build();
    }
}
