package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateStudentGuardianDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.UpdateStudentDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentGuardian;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.LegalGuardianRepository;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final IndividualPersonService personService;
    private final LegalGuardianRepository guardianRepository;

    public StudentService(StudentRepository studentRepository, 
                          IndividualPersonService personService,
                          LegalGuardianRepository guardianRepository) {
        this.studentRepository = studentRepository;
        this.personService = personService;
        this.guardianRepository = guardianRepository;
    }

    @Transactional
    public Student create(Student student, IndividualPerson person, List<CreateStudentGuardianDTO> guardianLinks) {
        IndividualPerson savedPerson = personService.create(person);
        student.setPersonId(savedPerson.getId());
        student.setActive(true);

        if (guardianLinks == null || guardianLinks.isEmpty()) {
            throw new IllegalArgumentException("Student must have at least one guardian.");
        }

        List<StudentGuardian> guardians = new ArrayList<>();
        for (CreateStudentGuardianDTO link : guardianLinks) {
            guardianRepository.findById(link.guardianId())
                    .orElseThrow(() -> new IllegalArgumentException("Guardian not found with id: " + link.guardianId()));
            
            StudentGuardian studentGuardian = new StudentGuardian();
            studentGuardian.setGuardianId(link.guardianId());
            studentGuardian.setKinshipId(link.kinshipId());
            guardians.add(studentGuardian);
        }
        student.setGuardians(guardians);

        return studentRepository.save(student);
    }

    @Transactional
    public Student update(Long id, UpdateStudentDTO dto) {
        Student existingStudent = findById(id);
        
        if (dto.studentData() != null) {
            if (dto.studentData().registrationOriginId() != null) {
                existingStudent.setRegistrationOriginId(dto.studentData().registrationOriginId());
            }
            if (dto.studentData().periodId() != null) {
                existingStudent.setPeriodId(dto.studentData().periodId());
            }
            existingStudent.setHasTransportAutonomy(dto.studentData().hasTransportAutonomy());
            if (dto.studentData().transportResponsibleName() != null) {
                existingStudent.setTransportResponsibleName(dto.studentData().transportResponsibleName());
            }
        }

        if (dto.active() != null) {
            existingStudent.setActive(dto.active());
        }

        // Atualização da Pessoa
        if (dto.person() != null && existingStudent.getPersonId() != null) {
            personService.update(existingStudent.getPersonId(), dto.person());
        }
        
        return studentRepository.save(existingStudent);
    }

    @Transactional
    public void inactivate(Long id) {
        Student existingStudent = findById(id);
        existingStudent.setActive(false);
        studentRepository.save(existingStudent);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
}
