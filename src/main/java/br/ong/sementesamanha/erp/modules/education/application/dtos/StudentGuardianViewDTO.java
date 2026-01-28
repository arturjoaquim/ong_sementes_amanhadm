package br.ong.sementesamanha.erp.modules.education.application.dtos;

import br.ong.sementesamanha.erp.modules.education.domain.entities.Person;
import lombok.Data;

@Data
public class StudentGuardianViewDTO {
    private Long id;
    private Long legalGuardianId;
    private Person person;
    private String kinshipName;
}
