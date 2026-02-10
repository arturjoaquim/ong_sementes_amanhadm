package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.IndividualPersonDTO;
import lombok.Data;

@Data
public class StudentGuardianResponseDTO {
    private Long id;
    private Long legalGuardianId;
    private IndividualPersonDTO person;
    private Long kinshipId;
}
