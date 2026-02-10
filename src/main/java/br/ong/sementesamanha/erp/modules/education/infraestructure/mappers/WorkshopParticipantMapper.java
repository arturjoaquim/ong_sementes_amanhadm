package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopParticipantResponseDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.WorkshopParticipant;
import org.springframework.stereotype.Component;

@Component
public class WorkshopParticipantMapper {

    public WorkshopParticipantResponseDTO toDTO(WorkshopParticipant entity) {
        if (entity == null) return null;
        return new WorkshopParticipantResponseDTO(
            entity.getId(),
            entity.getWorkshop() != null ? entity.getWorkshop().getId() : null,
            entity.getStudent().getId()
        );
    }
}
