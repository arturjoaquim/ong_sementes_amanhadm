package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.CreateStudentMedicationDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.StudentMedicationResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.UpdateStudentMedicationDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentMedication;
import org.springframework.stereotype.Component;

@Component
public class StudentMedicationMapper {
    public StudentMedicationResponseDTO toDTO(StudentMedication entity) {
        if (entity == null) return null;
        return new StudentMedicationResponseDTO(
            entity.getId(),
            entity.getMedicationName(),
            entity.getFrequency(),
            entity.getDosage()
        );
    }

    public StudentMedication toEntity(CreateStudentMedicationDTO dto) {
        if (dto == null) return null;
        StudentMedication entity = new StudentMedication();
        entity.setMedicationName(dto.medicationName());
        entity.setFrequency(dto.frequency());
        entity.setDosage(dto.dosage());
        return entity;
    }

    public void updateEntityFromDto(StudentMedication entity, UpdateStudentMedicationDTO dto) {
        if (dto == null || entity == null) return;
        if (dto.medicationName() != null) entity.setMedicationName(dto.medicationName());
        if (dto.frequency() != null) entity.setFrequency(dto.frequency());
        if (dto.dosage() != null) entity.setDosage(dto.dosage());
    }
}
