package br.ong.sementesamanha.erp.modules.education.application.dtos;

import java.util.List;

public record CreateWorkshopDTO(
    Long workshopTypeId,
    String description,
    String attendanceListUrl,
    Long educatorId,
    List<Long> studentIds
) {}
