package br.ong.sementesamanha.erp.modules.education.application.dtos;

public record CreateEducationDTO(
    String institution,
    Long periodId,
    Long educationLevelId,
    Long educationStatusId
) {}
