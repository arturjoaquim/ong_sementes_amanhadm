package br.ong.sementesamanha.erp.modules.education.application.dtos.workshop;

import java.util.List;

public record WorkshopDetailsResponseDTO(
    Long id,
    String name,
    Integer enrollmentLimit,
    Boolean active,
    List<WorkshopSessionDTO> sessions,
    List<WorkshopParticipantResponseDTO> enrolledStudents
) {}
