package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

import java.time.LocalDateTime;

public record StudentNoteResponseDTO(
    Long id,
    Boolean positive,
    String summary,
    String fullDescription,
    LocalDateTime occurrenceDate,
    Long registeredById
) {}
