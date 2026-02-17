package br.ong.sementesamanha.erp.modules.education.application.dtos.workshop;

import java.util.List;

public record WorkshopSessionDTO(
    Long id,
    String description,
    String attendanceListLink,
    Long responsibleEducatorId,
    String responsibleEducatorName,
    Integer participantsCount,
    List<WorkshopPresenceDTO> presences
) {}
