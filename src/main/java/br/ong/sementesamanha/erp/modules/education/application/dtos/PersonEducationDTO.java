package br.ong.sementesamanha.erp.modules.education.application.dtos;

public record PersonEducationDTO(
    String institution,
    Long periodId,
    Long educationLevelId,
    Long educationStatusId
) {}
