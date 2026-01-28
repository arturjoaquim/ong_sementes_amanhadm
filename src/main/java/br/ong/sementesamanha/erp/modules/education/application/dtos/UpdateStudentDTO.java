package br.ong.sementesamanha.erp.modules.education.application.dtos;

public record UpdateStudentDTO(
    StudentDataDTO studentData,
    IndividualPersonDTO person,
    Boolean active
) {}
