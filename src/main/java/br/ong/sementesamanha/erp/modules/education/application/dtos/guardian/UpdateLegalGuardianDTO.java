package br.ong.sementesamanha.erp.modules.education.application.dtos.guardian;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.IndividualPersonDTO;

public record UpdateLegalGuardianDTO(
    IndividualPersonDTO person
) {}
