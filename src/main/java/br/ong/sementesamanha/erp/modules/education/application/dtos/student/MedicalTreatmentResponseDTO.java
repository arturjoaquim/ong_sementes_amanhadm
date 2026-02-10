package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

public record MedicalTreatmentResponseDTO(
    Long id,
    String treatmentDescription,
    String observations,
    Long monitoringLocationId // Revertido para ID
) {}
