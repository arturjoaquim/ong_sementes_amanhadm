package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

import br.ong.sementesamanha.erp.modules.education.application.dtos.social.UpdateFamilyDTO;

public record UpdateHomeConditionDTO(
    Long parentsMaritalStatusId,
    Boolean keepsContactWithSpouse,
    Boolean staysAloneAtHome,
    String description,
    UpdateFamilyDTO family
) {}
