package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.StudentGuardianDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentGuardian;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.LegalGuardianRepository;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Student create(Student student, IndividualPerson person, List<StudentGuardianDTO> guardianLinks) {
        // 1. Salva a pessoa do estudante
        IndividualPerson savedPerson = personService.create(person);
        student.setPersonId(savedPerson.getId());
        student.setActive(true);

        // 2. Valida e prepara os vínculos
        if (guardianLinks == null || guardianLinks.isEmpty()) {
            throw new IllegalArgumentException("Student must have at least one guardian.");
        }

        Set<StudentGuardian> guardians = new HashSet<>();
        for (StudentGuardianDTO link : guardianLinks) {
            // Valida se o responsável existe
            guardianRepository.findById(link.guardianId())
                    .orElseThrow(() -> new IllegalArgumentException("Guardian not found with id: " + link.guardianId()));
            
            StudentGuardian studentGuardian = new StudentGuardian();
            studentGuardian.setGuardianId(link.guardianId());
            studentGuardian.setKinshipId(link.kinshipId());
            guardians.add(studentGuardian);
        }
        student.setGuardians(guardians);

        // 3. Salva o estudante com os vínculos em cascata
        return studentRepository.save(student);
    }

    @Transactional
    public Student update(Long id, Student studentData) {
        Student existingStudent = findById(id);
        
        if (studentData.getRegistrationOriginId() != null) {
            existingStudent.setRegistrationOriginId(studentData.getRegistrationOriginId());
        }
        if (studentData.getPeriodId() != null) {
            existingStudent.setPeriodId(studentData.getPeriodId());
        }
        existingStudent.setHasTransportAutonomy(studentData.isHasTransportAutonomy());
        if (studentData.getTransportResponsibleName() != null) {
            existingStudent.setTransportResponsibleName(studentData.getTransportResponsibleName());
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
