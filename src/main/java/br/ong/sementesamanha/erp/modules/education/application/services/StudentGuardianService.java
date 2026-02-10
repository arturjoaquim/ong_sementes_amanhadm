package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.CreateStudentGuardianDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.UpdateStudentGuardianDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.LegalGuardian;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentGuardian;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentGuardianId;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.LegalGuardianRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.StudentGuardianRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentGuardianService {

    private final StudentGuardianRepository studentGuardianRepository;
    private final StudentRepository studentRepository;
    private final LegalGuardianRepository legalGuardianRepository;

    public StudentGuardianService(StudentGuardianRepository studentGuardianRepository,
                                  StudentRepository studentRepository,
                                  LegalGuardianRepository legalGuardianRepository) {
        this.studentGuardianRepository = studentGuardianRepository;
        this.studentRepository = studentRepository;
        this.legalGuardianRepository = legalGuardianRepository;
    }

    @Transactional
    public void addGuardian(Long studentId, CreateStudentGuardianDTO dto) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studentId));
        
        LegalGuardian guardian = legalGuardianRepository.findById(dto.guardianId())
                .orElseThrow(() -> new IllegalArgumentException("Guardian not found with id: " + dto.guardianId()));

        StudentGuardian sg = new StudentGuardian();
        sg.setId(new StudentGuardianId(studentId, dto.guardianId()));
        sg.setStudent(student);
        sg.setGuardian(guardian);
        sg.setKinshipId(dto.kinshipId());

        studentGuardianRepository.save(sg);
    }

    @Transactional
    public void updateGuardian(Long studentId, Long guardianId, UpdateStudentGuardianDTO dto) {
        StudentGuardian sg = studentGuardianRepository.findById(new StudentGuardianId(studentId, guardianId))
                .orElseThrow(() -> new IllegalArgumentException("StudentGuardian relationship not found"));

        if (dto.kinshipId() != null) {
            sg.setKinshipId(dto.kinshipId());
        }

        studentGuardianRepository.save(sg);
    }

    @Transactional
    public void removeGuardian(Long studentId, Long guardianId) {
        StudentGuardianId id = new StudentGuardianId(studentId, guardianId);
        if (!studentGuardianRepository.existsById(id)) {
            throw new IllegalArgumentException("StudentGuardian relationship not found");
        }
        studentGuardianRepository.deleteById(id);
    }
}
