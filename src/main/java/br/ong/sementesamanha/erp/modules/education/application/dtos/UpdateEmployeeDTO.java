package br.ong.sementesamanha.erp.modules.education.application.dtos;

public record UpdateEmployeeDTO(
    IndividualPersonDTO person,
    Long positionId,
    Long systemUserId
) {}
