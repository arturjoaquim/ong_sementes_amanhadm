package br.ong.sementesamanha.erp.modules.education.application.dtos.workshop;

public record WorkshopPreviewResponseDTO(
    Long id,
    String name,
    Integer enrollmentLimit,
    Boolean active,
    Integer sessionsCount
) {}
