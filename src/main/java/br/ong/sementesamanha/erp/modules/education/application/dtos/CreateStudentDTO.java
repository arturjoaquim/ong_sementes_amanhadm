package br.ong.sementesamanha.erp.modules.education.application.dtos;

import java.util.Date;

public record CreateStudentDTO(
    CreatePersonDTO person,
    StudentDataDTO studentData,
    Date registrationDate
) {}
