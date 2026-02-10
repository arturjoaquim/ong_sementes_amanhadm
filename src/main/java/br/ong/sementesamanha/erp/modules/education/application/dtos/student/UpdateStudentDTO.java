package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.IndividualPersonDTO;
import java.util.List;

public record UpdateStudentDTO(
    StudentAbstractDTO studentData,
    IndividualPersonDTO person,
    Boolean active,
    UpdateStudentHealthDTO healthData,
    UpdateHomeConditionDTO dwellingCondition,
    List<CreateStudentGuardianDTO> guardians
) {}
