package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

import br.ong.sementesamanha.erp.modules.education.application.dtos.social.CreateFamilyDTO;

public record CreateHomeConditionDTO(
	Long studentId,
    CreateFamilyDTO family,
    Long parentsMaritalStatusId,
    Boolean keepsContactWithSpouse,
    Boolean staysAloneAtHome,
    String description
) {}
