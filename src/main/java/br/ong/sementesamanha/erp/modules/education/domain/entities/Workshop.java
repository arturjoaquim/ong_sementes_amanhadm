package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;
import java.util.Set;

@Data
public class Workshop {
    private Long id;
    private Long workshopTypeId;
    private String description;
    private String attendanceListUrl;
    private Long educatorId;
    private Set<WorkshopParticipant> participants;
}
