package br.ong.sementesamanha.erp.modules.education.application.dtos;

public record CreateEmployeeDTO(
    IndividualPersonDTO person,
    Long positionId,
    Long systemUserId
) {}
