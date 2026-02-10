package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.CreateStudentDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.StudentDetailsResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.StudentPreviewResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.UpdateStudentDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.StudentService;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.filters.StudentFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/preview")
    public List<StudentPreviewResponseDTO> getStudentsPreview(@ModelAttribute StudentFilter filter) {
        return studentService.getStudentsPreview(filter);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<StudentDetailsResponseDTO> getStudentDetails(@PathVariable Long id) {
        return studentService.getStudentDetails(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateStudentDTO dto) {
        Student created = studentService.create(dto);
        return ResponseEntity.created(URI.create("/students/" + created.getId())).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateStudentDTO dto) {
        studentService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inactivate")
    public ResponseEntity<Void> inactivate(@PathVariable Long id) {
        studentService.inactivate(id);
        return ResponseEntity.noContent().build();
    }
}
