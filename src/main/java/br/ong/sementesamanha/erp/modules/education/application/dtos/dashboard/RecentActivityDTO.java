package br.ong.sementesamanha.erp.modules.education.application.dtos.dashboard;

import java.time.LocalDateTime;

public record RecentActivityDTO(
    Long id,
    String title,
    String description,
    LocalDateTime timestamp,
    String type // "STUDENT", "WORKSHOP", "EMPLOYEE"
) {}
