package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.*;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.LegalGuardian;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentGuardian;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentGuardianId;
import br.ong.sementesamanha.erp.modules.education.domain.filters.StudentFilter;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.IndividualPersonMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.StudentMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.LegalGuardianRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.StudentGuardianRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.StudentRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.specifications.StudentSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final IndividualPersonService personService;
    private final LegalGuardianRepository guardianRepository;
    private final StudentGuardianRepository studentGuardianRepository;
    private final StudentMapper studentMapper;
    private final IndividualPersonMapper individualPersonMapper;

    public StudentService(StudentRepository studentRepository, 
                          IndividualPersonService personService,
                          LegalGuardianRepository guardianRepository,
                          StudentGuardianRepository studentGuardianRepository,
                          StudentMapper studentMapper,
                          IndividualPersonMapper individualPersonMapper) {
        this.studentRepository = studentRepository;
        this.personService = personService;
        this.guardianRepository = guardianRepository;
        this.studentGuardianRepository = studentGuardianRepository;
        this.studentMapper = studentMapper;
        this.individualPersonMapper = individualPersonMapper;
    }

    @Transactional(readOnly = true)
    public List<StudentPreviewResponseDTO> getStudentsPreview(StudentFilter filter) {
        Specification<Student> spec = StudentSpecification.withFilter(filter)
                .and(StudentSpecification.fetchForPreview());

        return studentRepository.findAll(spec).stream()
                .map(studentMapper::toPreview)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<StudentDetailsResponseDTO> getStudentDetails(Long id) {
        Specification<Student> spec = Specification.where((root, query, cb) -> cb.equal(root.get("id"), id));
        spec = spec.and(StudentSpecification.fetchForDetails());
        
        return studentRepository.findOne(spec)
                .map(studentMapper::toDetails);
    }

    @Transactional
    public Student create(CreateStudentDTO dto) {
        IndividualPerson person = individualPersonMapper.toDomain(dto.person());
        IndividualPerson savedPerson = personService.create(person);
        
        Student student = studentMapper.toDomain(dto);
        student.setPerson(savedPerson);
        student.setActive(true);
        
        Student savedStudent = studentRepository.save(student);

        if (dto.guardians() == null || dto.guardians().isEmpty()) {
            throw new IllegalArgumentException("Student must have at least one guardian.");
        }

        for (CreateStudentGuardianDTO link : dto.guardians()) {
            LegalGuardian guardian = guardianRepository.findById(link.guardianId())
                    .orElseThrow(() -> new IllegalArgumentException("Guardian not found with id: " + link.guardianId()));
            
            StudentGuardian joinEntry = new StudentGuardian();
            joinEntry.setId(new StudentGuardianId(savedStudent.getId(), guardian.getId()));
            joinEntry.setStudent(savedStudent);
            joinEntry.setGuardian(guardian);
            joinEntry.setKinshipId(link.kinshipId());
            
            studentGuardianRepository.save(joinEntry);
        }

        return savedStudent;
    }

    @Transactional
    public Student update(Long id, UpdateStudentDTO dto) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        
        studentMapper.updateDomainFromDTO(existingStudent,dto);

        if (dto.person() != null && existingStudent.getPerson() != null) {
            personService.update(existingStudent.getPerson().getId(), dto.person());
        }
        
        return studentRepository.save(existingStudent);
    }

    @Transactional
    public void inactivate(Long id) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        existingStudent.setActive(false);
        studentRepository.save(existingStudent);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
}
