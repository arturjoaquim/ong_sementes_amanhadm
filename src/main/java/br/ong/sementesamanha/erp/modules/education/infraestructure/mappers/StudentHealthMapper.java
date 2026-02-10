package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.CreateStudentHealthDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.StudentHealthResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.UpdateStudentHealthDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentHealth;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class StudentHealthMapper {

    private final StudentMedicationMapper medicationMapper;
    private final MedicalTreatmentMapper treatmentMapper;

    public StudentHealthMapper(StudentMedicationMapper medicationMapper, MedicalTreatmentMapper treatmentMapper) {
        this.medicationMapper = medicationMapper;
        this.treatmentMapper = treatmentMapper;
    }

    public StudentHealthResponseDTO toDTO(StudentHealth entity) {
        if (entity == null) return null;
        return new StudentHealthResponseDTO(
            entity.getId(),
            entity.getUbsReference(),
            entity.getWearsGlasses(),
            entity.getInfoExpirationDate(),
            entity.getMedications() != null ? entity.getMedications().stream().map(medicationMapper::toDTO).collect(Collectors.toList()) : Collections.emptyList(),
            entity.getTreatments() != null ? entity.getTreatments().stream().map(treatmentMapper::toDTO).collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    public StudentHealth toEntity(CreateStudentHealthDTO dto, Student student) {
        if (dto == null) return null;
        StudentHealth entity = new StudentHealth();
        entity.setStudent(student);
        entity.setUbsReference(dto.ubsReference());
        entity.setWearsGlasses(dto.wearsGlasses());
        entity.setInfoExpirationDate(dto.infoExpirationDate());
        
        if (dto.medications() != null) {
            entity.setMedications(dto.medications().stream()
                    .map(m -> {
                        var med = medicationMapper.toEntity(m);
                        med.setHealthRecord(entity);
                        return med;
                    })
                    .collect(Collectors.toSet()));
        }
        
        if (dto.treatments() != null) {
            entity.setTreatments(dto.treatments().stream()
                    .map(t -> {
                        var treat = treatmentMapper.toEntity(t);
                        treat.setHealthRecord(entity);
                        return treat;
                    })
                    .collect(Collectors.toSet()));
        }
        
        return entity;
    }

    public void updateEntityFromDto(StudentHealth entity, UpdateStudentHealthDTO dto) {
        if (dto == null || entity == null) return;

        if (dto.ubsReference() != null) entity.setUbsReference(dto.ubsReference());
        if (dto.wearsGlasses() != null) entity.setWearsGlasses(dto.wearsGlasses());
        if (dto.infoExpirationDate() != null) entity.setInfoExpirationDate(dto.infoExpirationDate());
        
        // Listas de medicamentos e tratamentos são gerenciadas por endpoints específicos
    }
}
