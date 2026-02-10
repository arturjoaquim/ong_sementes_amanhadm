package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.CreateMedicalTreatmentDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.CreateStudentMedicationDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.UpdateMedicalTreatmentDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.UpdateStudentMedicationDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.StudentHealthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students/{studentId}/health")
public class StudentHealthController {

    private final StudentHealthService service;

    public StudentHealthController(StudentHealthService service) {
        this.service = service;
    }

    @PostMapping("/medications")
    public ResponseEntity<Void> addMedication(@PathVariable Long studentId, @RequestBody CreateStudentMedicationDTO dto) {
        service.addMedication(studentId, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/medications/{medicationId}")
    public ResponseEntity<Void> updateMedication(@PathVariable Long studentId, @PathVariable Long medicationId, @RequestBody UpdateStudentMedicationDTO dto) {
        service.updateMedication(studentId, medicationId, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/medications/{medicationId}")
    public ResponseEntity<Void> removeMedication(@PathVariable Long studentId, @PathVariable Long medicationId) {
        service.removeMedication(studentId, medicationId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/treatments")
    public ResponseEntity<Void> addTreatment(@PathVariable Long studentId, @RequestBody CreateMedicalTreatmentDTO dto) {
        service.addTreatment(studentId, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/treatments/{treatmentId}")
    public ResponseEntity<Void> updateTreatment(@PathVariable Long studentId, @PathVariable Long treatmentId, @RequestBody UpdateMedicalTreatmentDTO dto) {
        service.updateTreatment(studentId, treatmentId, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/treatments/{treatmentId}")
    public ResponseEntity<Void> removeTreatment(@PathVariable Long studentId, @PathVariable Long treatmentId) {
        service.removeTreatment(studentId, treatmentId);
        return ResponseEntity.noContent().build();
    }
}
