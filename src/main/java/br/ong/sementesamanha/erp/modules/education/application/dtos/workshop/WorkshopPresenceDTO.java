package br.ong.sementesamanha.erp.modules.education.application.dtos.workshop;

public record WorkshopPresenceDTO(
    Long id,
    Long studentId,
    String studentName
) {}
