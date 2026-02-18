package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

public record StudentPreviewResponseDTO(
    Long id,
    String studentName,
    String guardianName,
    String guardianPhone,
    String status,
    Float attendance,
    Long educationLevelId, // Revertido para ID
	Integer age
) {}
