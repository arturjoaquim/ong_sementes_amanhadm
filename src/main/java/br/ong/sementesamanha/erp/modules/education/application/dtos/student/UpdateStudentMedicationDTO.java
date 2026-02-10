package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

public record UpdateStudentMedicationDTO(
    String medicationName,
    String frequency,
    String dosage
) {}
