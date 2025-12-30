package br.ong.sementesamanha.erp.modules.education.application.dtos;

public record StudentDataDTO(
    Long registrationOriginId,
    Long periodId,
    boolean hasTransportAutonomy,
    String transportResponsibleName
) {}
