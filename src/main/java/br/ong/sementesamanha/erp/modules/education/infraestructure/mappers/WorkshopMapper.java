package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.domain.entities.Workshop;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.WorkshopModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.rh.EmployeeModel;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class WorkshopMapper {

    private final WorkshopParticipantMapper participantMapper;

    public WorkshopMapper(WorkshopParticipantMapper participantMapper) {
        this.participantMapper = participantMapper;
    }

    public Workshop toDomain(WorkshopModel model) {
        if (model == null) return null;
        Workshop domain = new Workshop();
        domain.setId(model.getId());
        domain.setWorkshopTypeId(model.getWorkshopTypeId());
        domain.setDescription(model.getDescription());
        domain.setAttendanceListUrl(model.getAttendanceListLink());
        if (model.getResponsibleEducator() != null) {
            domain.setEducatorId(model.getResponsibleEducator().getId());
        }
        if (model.getParticipants() != null) {
            domain.setParticipants(model.getParticipants().stream()
                    .map(participantMapper::toDomain)
                    .collect(Collectors.toSet()));
        }
        return domain;
    }

    public WorkshopModel toModel(Workshop domain) {
        if (domain == null) return null;
        WorkshopModel model = new WorkshopModel();
        model.setId(domain.getId());
        model.setWorkshopTypeId(domain.getWorkshopTypeId());
        model.setDescription(domain.getDescription());
        model.setAttendanceListLink(domain.getAttendanceListUrl());
        if (domain.getEducatorId() != null) {
            EmployeeModel educator = new EmployeeModel();
            educator.setId(domain.getEducatorId());
            model.setResponsibleEducator(educator);
        }
        if (domain.getParticipants() != null) {
            model.setParticipants(domain.getParticipants().stream()
                    .map(p -> participantMapper.toModel(p, model))
                    .collect(Collectors.toList()));
        }
        return model;
    }
}
