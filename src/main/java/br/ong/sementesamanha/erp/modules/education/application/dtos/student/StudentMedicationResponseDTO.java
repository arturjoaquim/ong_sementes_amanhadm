package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

public record StudentMedicationResponseDTO(
    Long id,
    String medicationName,
    String frequency,
    String dosage
) {}
