package br.ong.sementesamanha.erp.modules.education.application.dtos.employee;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.IndividualPersonDTO;

public record EmployeeResponseDTO(
    Long id,
    IndividualPersonDTO person,
    Long positionId,
    Long systemUserId
) {}
