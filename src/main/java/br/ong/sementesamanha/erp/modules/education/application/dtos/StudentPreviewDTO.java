package br.ong.sementesamanha.erp.modules.education.application.dtos;

public record StudentPreviewDTO(
        long id,
        String name,
        String guardianName,
        String guardianPhone,
        String status,
        float attendance,
        String grade,
        Integer age
) {
}