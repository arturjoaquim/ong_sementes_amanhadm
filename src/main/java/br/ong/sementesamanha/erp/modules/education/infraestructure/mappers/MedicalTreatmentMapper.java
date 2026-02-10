package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.CreateMedicalTreatmentDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.MedicalTreatmentResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.UpdateMedicalTreatmentDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.MedicalTreatment;
import org.springframework.stereotype.Component;

@Component
public class MedicalTreatmentMapper {

    public MedicalTreatmentMapper() {
    }

    public MedicalTreatmentResponseDTO toDTO(MedicalTreatment entity) {
        if (entity == null) return null;

        return new MedicalTreatmentResponseDTO(
            entity.getId(),
            entity.getTreatmentDescription(),
            entity.getObservations(),
            entity.getMonitoringLocationId()
        );
    }

    public MedicalTreatment toEntity(CreateMedicalTreatmentDTO dto) {
        if (dto == null) return null;
        MedicalTreatment entity = new MedicalTreatment();
        entity.setTreatmentDescription(dto.treatmentDescription());
        entity.setObservations(dto.observations());
        entity.setMonitoringLocationId(dto.monitoringLocationId());
        return entity;
    }

    public void updateEntityFromDto(MedicalTreatment entity, UpdateMedicalTreatmentDTO dto) {
        if (dto == null || entity == null) return;
        if (dto.treatmentDescription() != null) entity.setTreatmentDescription(dto.treatmentDescription());
        if (dto.observations() != null) entity.setObservations(dto.observations());
        if (dto.monitoringLocationId() != null) entity.setMonitoringLocationId(dto.monitoringLocationId());
    }
}
