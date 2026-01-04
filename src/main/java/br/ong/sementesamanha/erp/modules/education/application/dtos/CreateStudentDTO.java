package br.ong.sementesamanha.erp.modules.education.application.dtos;

import java.util.Date;
import java.util.List;

public record CreateStudentDTO(
    CreatePersonDTO person,
    StudentDataDTO studentData,
    List<StudentGuardianDTO> guardians,
    Date registrationDate
) {}
