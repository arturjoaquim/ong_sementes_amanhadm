package br.ong.sementesamanha.erp.modules.education.domain.projections;
public record StudentPreview(
        Long id,
        String studentName,
        String guardianName,
        String guardianPhone,
        boolean active,
        String grade,
        Integer age
) {
}