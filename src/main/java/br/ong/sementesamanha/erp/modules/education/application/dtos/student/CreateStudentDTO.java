package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.IndividualPersonDTO;
import java.util.Date;
import java.util.List;

public record CreateStudentDTO(
    IndividualPersonDTO person,
    StudentAbstractDTO studentData,
    List<CreateStudentGuardianDTO> guardians,
    Date registrationDate,
    CreateStudentHealthDTO healthData,
    CreateHomeConditionDTO dwellingCondition
) {}
