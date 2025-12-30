package br.ong.sementesamanha.erp.modules.education.domain.services;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.ports.IndividualPersonServicePort;
import br.ong.sementesamanha.erp.modules.education.domain.ports.StudentRepositoryPort;
import br.ong.sementesamanha.erp.modules.education.domain.ports.StudentServicePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService implements StudentServicePort {

    private final StudentRepositoryPort studentRepository;
    private final IndividualPersonServicePort personService;

    public StudentService(StudentRepositoryPort studentRepository, IndividualPersonServicePort personService) {
        this.studentRepository = studentRepository;
        this.personService = personService;
    }

    @Override
    @Transactional
    public Student create(Student student, IndividualPerson person) {
        if (person != null) {
            IndividualPerson savedPerson = personService.create(person);
            student.setPersonId(savedPerson.getId());
        } else if (student.getPersonId() == null) {
            throw new IllegalArgumentException("Student must have a Person associated (either object or ID).");
        }
        
        student.setActive(true);
        return studentRepository.save(student);
    }

    @Override
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
        if (studentData.isActive()) {
             existingStudent.setActive(studentData.isActive());
        }
        
        return studentRepository.save(existingStudent);
    }

    @Override
    @Transactional
    public void inactivate(Long id) {
        Student existingStudent = findById(id);
        existingStudent.setActive(false);
        studentRepository.save(existingStudent);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
}
