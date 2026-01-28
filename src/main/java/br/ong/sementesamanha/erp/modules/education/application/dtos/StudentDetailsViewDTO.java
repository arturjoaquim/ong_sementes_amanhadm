package br.ong.sementesamanha.erp.modules.education.application.dtos;

import br.ong.sementesamanha.erp.modules.education.domain.entities.*;

import java.util.Date;
import java.util.List;

public record StudentDetailsViewDTO(
    Long id,
    IndividualPerson personData,
    Date enrollmentDate,
    String status,
    Double attendance,
    Long periodId,
    String enrollmentOrigin,
    Boolean accompaniedStatus,
    String transportGuardianName,
    PersonContact emergencyContact,
    List<StudentNote> notes,
    StudentHealth healthData,
    HomeCondition dwellingCondition,
    List<SocialInteraction> socialInteractions,
    List<StudentGuardianViewDTO> guardianRelashionship,
    List<WorkshopParticipant> socialActivities
) {}
