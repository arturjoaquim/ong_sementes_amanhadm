package br.ong.sementesamanha.erp.modules.education.application.dtos;

import java.util.Date;
import java.util.List;

public record CreateStudentDTO(
    IndividualPersonDTO person,
    StudentDataDTO studentData,
    List<CreateStudentGuardianDTO> guardians,
    Date registrationDate
) {}
