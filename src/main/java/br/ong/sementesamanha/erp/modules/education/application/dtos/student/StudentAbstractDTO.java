package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

public record StudentAbstractDTO(
    Long registrationOriginId,
    Long periodId,
    Boolean hasTransportAutonomy,
    String transportResponsibleName
) {}
