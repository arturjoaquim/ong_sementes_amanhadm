package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.CreateMedicalTreatmentDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.CreateStudentMedicationDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.UpdateMedicalTreatmentDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.UpdateStudentMedicationDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.MedicalTreatment;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentHealth;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentMedication;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.MedicalTreatmentMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.StudentMedicationMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class StudentHealthService {

    private final StudentRepository studentRepository;
    private final StudentMedicationMapper medicationMapper;
    private final MedicalTreatmentMapper treatmentMapper;

    public StudentHealthService(StudentRepository studentRepository,
                                StudentMedicationMapper medicationMapper,
                                MedicalTreatmentMapper treatmentMapper) {
        this.studentRepository = studentRepository;
        this.medicationMapper = medicationMapper;
        this.treatmentMapper = treatmentMapper;
    }

    private StudentHealth getOrCreateHealthRecord(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studentId));

        if (student.getHealthRecord() == null) {
            StudentHealth health = new StudentHealth();
            health.setStudent(student);
            student.setHealthRecord(health);
        }
        return student.getHealthRecord();
    }

    @Transactional
    public void addMedication(Long studentId, CreateStudentMedicationDTO dto) {
        StudentHealth health = getOrCreateHealthRecord(studentId);
        
        if (health.getMedications() == null) {
            health.setMedications(new HashSet<>());
        }

        StudentMedication medication = medicationMapper.toEntity(dto);
        medication.setHealthRecord(health);
        health.getMedications().add(medication);

        studentRepository.save(health.getStudent());
    }

    @Transactional
    public void updateMedication(Long studentId, Long medicationId, UpdateStudentMedicationDTO dto) {
        StudentHealth health = getOrCreateHealthRecord(studentId);
        
        if (health.getMedications() != null) {
            StudentMedication medication = health.getMedications().stream()
                    .filter(m -> m.getId() != null && m.getId().equals(medicationId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Medication not found with id: " + medicationId));
            
            medicationMapper.updateEntityFromDto(medication, dto);
            studentRepository.save(health.getStudent());
        }
    }

    @Transactional
    public void removeMedication(Long studentId, Long medicationId) {
        StudentHealth health = getOrCreateHealthRecord(studentId);
        
        if (health.getMedications() != null) {
            boolean removed = health.getMedications().removeIf(m -> m.getId() != null && m.getId().equals(medicationId));
            if (removed) {
                studentRepository.save(health.getStudent());
            } else {
                throw new IllegalArgumentException("Medication not found with id: " + medicationId);
            }
        }
    }

    @Transactional
    public void addTreatment(Long studentId, CreateMedicalTreatmentDTO dto) {
        StudentHealth health = getOrCreateHealthRecord(studentId);
        
        if (health.getTreatments() == null) {
            health.setTreatments(new HashSet<>());
        }

        MedicalTreatment treatment = treatmentMapper.toEntity(dto);
        treatment.setHealthRecord(health);
        health.getTreatments().add(treatment);

        studentRepository.save(health.getStudent());
    }

    @Transactional
    public void updateTreatment(Long studentId, Long treatmentId, UpdateMedicalTreatmentDTO dto) {
        StudentHealth health = getOrCreateHealthRecord(studentId);
        
        if (health.getTreatments() != null) {
            MedicalTreatment treatment = health.getTreatments().stream()
                    .filter(t -> t.getId() != null && t.getId().equals(treatmentId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Treatment not found with id: " + treatmentId));
            
            treatmentMapper.updateEntityFromDto(treatment, dto);
            studentRepository.save(health.getStudent());
        }
    }

    @Transactional
    public void removeTreatment(Long studentId, Long treatmentId) {
        StudentHealth health = getOrCreateHealthRecord(studentId);
        
        if (health.getTreatments() != null) {
            boolean removed = health.getTreatments().removeIf(t -> t.getId() != null && t.getId().equals(treatmentId));
            if (removed) {
                studentRepository.save(health.getStudent());
            } else {
                throw new IllegalArgumentException("Treatment not found with id: " + treatmentId);
            }
        }
    }
}
