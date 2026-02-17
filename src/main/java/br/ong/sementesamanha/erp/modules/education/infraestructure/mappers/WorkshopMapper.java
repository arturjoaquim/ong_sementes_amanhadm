package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopDetailsResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopParticipantResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopPreviewResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopSessionDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopSessionResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopPresenceDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Workshop;
import br.ong.sementesamanha.erp.modules.education.domain.entities.WorkshopSession;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class WorkshopMapper {

    public WorkshopPreviewResponseDTO toPreview(Workshop entity) {
        if (entity == null) return null;

        return new WorkshopPreviewResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getEnrollmentLimit(),
            entity.getActive(),
            entity.getSessions() != null ? entity.getSessions().size() : 0
        );
    }

    public WorkshopDetailsResponseDTO toDetails(Workshop entity) {
        if (entity == null) return null;

        return new WorkshopDetailsResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getEnrollmentLimit(),
            entity.getActive(),
            entity.getSessions() != null ? entity.getSessions().stream()
                    .map(this::toSessionDTO)
                    .collect(Collectors.toList()) : Collections.emptyList(),
            entity.getEnrollments() != null ? entity.getEnrollments().stream()
                    .map(e -> new WorkshopParticipantResponseDTO(
                            e.getId(),
                            e.getWorkshop().getId(),
                            e.getStudent().getId(),
                            e.getStudent().getPerson().getPersonName()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    private WorkshopSessionDTO toSessionDTO(WorkshopSession session) {
        String educatorName = "N/A";
        Long educatorId = null;
        if (session.getResponsibleEducator() != null) {
            educatorId = session.getResponsibleEducator().getId();
            if (session.getResponsibleEducator().getPerson() != null) {
                educatorName = session.getResponsibleEducator().getPerson().getPersonName();
            }
        }

        return new WorkshopSessionDTO(
            session.getId(),
            session.getDescription(),
            session.getAttendanceListLink(),
            educatorId,
            educatorName,
            session.getParticipants() != null ? session.getParticipants().size() : 0,
            session.getParticipants() != null ? session.getParticipants().stream()
                    .map(p -> new WorkshopPresenceDTO(
                            p.getId(),
                            p.getWorkshopEnrollment().getStudent().getId(),
                            p.getWorkshopEnrollment().getStudent().getPerson().getPersonName()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    public WorkshopSessionResponseDTO toSessionResponseDTO(WorkshopSession session) {
        if (session == null) return null;

        String educatorName = "N/A";
        Long educatorId = null;
        if (session.getResponsibleEducator() != null) {
            educatorId = session.getResponsibleEducator().getId();
            if (session.getResponsibleEducator().getPerson() != null) {
                educatorName = session.getResponsibleEducator().getPerson().getPersonName();
            }
        }

        return new WorkshopSessionResponseDTO(
            session.getId(),
            session.getDescription(),
            session.getAttendanceListLink(),
            educatorId,
            educatorName,
            session.getWorkshop() != null ? session.getWorkshop().getId() : null,
            session.getWorkshop() != null ? session.getWorkshop().getName() : "N/A",
            session.getParticipants() != null ? session.getParticipants().size() : 0
        );
    }
}
