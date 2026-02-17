package br.ong.sementesamanha.erp.modules.education.application.dtos.employee;

public record EmployeePreviewResponseDTO(
    Long id,
    String name,
    Long positionId,
    String email,
    String phone
) {}
