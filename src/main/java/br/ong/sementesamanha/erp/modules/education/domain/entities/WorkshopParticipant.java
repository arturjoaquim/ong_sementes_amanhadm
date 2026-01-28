package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;

@Data
public class WorkshopParticipant {
    private Long id;
    private Long workshopId;
    private Long studentId;
}
