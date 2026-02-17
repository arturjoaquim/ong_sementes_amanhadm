package br.ong.sementesamanha.erp.modules.education.application.dtos.workshop;

public record WorkshopSessionResponseDTO(
    Long id,
    String description,
    String attendanceListLink,
    Long responsibleEducatorId,
    String responsibleEducatorName,
    Long workshopId,
    String workshopName,
    Integer participantsCount
) {}
