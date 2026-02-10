package br.ong.sementesamanha.erp.modules.education.application.dtos.workshop;

public record WorkshopParticipantResponseDTO (
        Long id,
        Long workshopId,
        Long studentId
) {}
