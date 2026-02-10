package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

public record UpdateMedicalTreatmentDTO(
    String treatmentDescription,
    String observations,
    Long monitoringLocationId
) {}
