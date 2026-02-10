package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

import br.ong.sementesamanha.erp.modules.education.application.dtos.social.FamilyResponseDTO;

public record HomeConditionResponseDTO(
    Long id,
    FamilyResponseDTO family,
    Long parentsMaritalStatusId,
    Boolean keepsContactWithSpouse,
    Boolean staysAloneAtHome
) {}
