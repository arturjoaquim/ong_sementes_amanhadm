package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

public record CreateMedicalTreatmentDTO(
    String treatmentDescription,
    String observations,
    Long monitoringLocationId
) {}
