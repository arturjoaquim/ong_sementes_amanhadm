package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

import java.time.LocalDate;

public record UpdateStudentHealthDTO(
    String ubsReference,
    Boolean wearsGlasses,
    LocalDate infoExpirationDate
) {}
