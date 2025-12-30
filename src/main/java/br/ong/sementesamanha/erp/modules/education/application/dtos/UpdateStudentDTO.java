package br.ong.sementesamanha.erp.modules.education.application.dtos;

public record UpdateStudentDTO(
    StudentDataDTO studentData,
    Boolean active
) {}
