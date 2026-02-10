package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

public record CreateStudentMedicationDTO(
    String medicationName,
    String frequency,
    String dosage
) {}
