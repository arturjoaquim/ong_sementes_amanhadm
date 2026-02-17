package br.ong.sementesamanha.erp.modules.education.application.dtos.workshop;

import java.util.List;

public record CreateWorkshopSessionDTO(
    Long workshopId,
    String description,
    String attendanceListLink,
    Long responsibleEducatorId,
    List<Long> studentIds
) {}
