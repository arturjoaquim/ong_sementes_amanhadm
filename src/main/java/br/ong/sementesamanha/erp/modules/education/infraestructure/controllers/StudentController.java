package br.ong.sementesamanha.erp.modules.education.infraestructure.controllers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateStudentDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.StudentPreviewDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.UpdateStudentDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.StudentService;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.filters.StudentFilter;
import br.ong.sementesamanha.erp.modules.education.domain.projections.StudentPreview;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.StudentRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.IndividualPersonMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.StudentMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final IndividualPersonMapper individualPersonMapper;

    public StudentController(StudentRepository studentRepository, 
                             StudentService studentService,
                             StudentMapper studentMapper,
                             IndividualPersonMapper individualPersonMapper) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.individualPersonMapper = individualPersonMapper;
    }

    @GetMapping("/preview")
    public List<StudentPreviewDTO> getStudentsPreview(@ModelAttribute StudentFilter filter) {
        List<StudentPreview> previews = studentRepository.findAllPreviews(filter);

        return previews.stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateStudentDTO dto) {
        Student student = studentMapper.toDomain(dto);
        IndividualPerson person = individualPersonMapper.toDomain(dto.person());
        
        Student created = studentService.create(student, person, dto.guardians());
        return ResponseEntity.created(URI.create("/students/" + created.getId())).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateStudentDTO dto) {
        Student student = studentMapper.toDomain(dto);
        studentService.update(id, student);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inactivate")
    public ResponseEntity<Void> inactivate(@PathVariable Long id) {
        studentService.inactivate(id);
        return ResponseEntity.noContent().build();
    }
}
