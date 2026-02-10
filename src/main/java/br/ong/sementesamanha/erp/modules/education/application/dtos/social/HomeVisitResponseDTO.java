package br.ong.sementesamanha.erp.modules.education.application.dtos.social;

import java.time.LocalDate;

public record HomeVisitResponseDTO(
    Long id,
    LocalDate visitDate,
    String summary,
    String fullReport,
    Long visitorId
) {}
