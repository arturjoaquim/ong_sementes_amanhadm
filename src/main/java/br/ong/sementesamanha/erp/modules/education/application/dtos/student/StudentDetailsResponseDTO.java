package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.IndividualPersonDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.person.PersonContactDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.workshop.WorkshopParticipantResponseDTO;

import java.util.Date;
import java.util.List;

public record StudentDetailsResponseDTO(
    Long id,
    IndividualPersonDTO personData,
    Date enrollmentDate,
    String status,
    Double attendance,
    Long periodId, // Revertido para ID
    Long registrationOriginId, // Revertido para ID
    Boolean accompaniedStatus,
    String transportGuardianName,
    PersonContactDTO emergencyContact,
    List<StudentNoteResponseDTO> notes,
    StudentHealthResponseDTO healthData,
    HomeConditionResponseDTO dwellingCondition,
    List<SocialInteractionResponseDTO> socialInteractions,
    List<StudentGuardianResponseDTO> guardianRelashionship,
    List<WorkshopParticipantResponseDTO> socialActivities
) {}
