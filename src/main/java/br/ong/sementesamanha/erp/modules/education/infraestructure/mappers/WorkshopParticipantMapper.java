package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.domain.entities.WorkshopParticipant;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.WorkshopModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.WorkshopParticipantModel;
import org.springframework.stereotype.Component;

@Component
public class WorkshopParticipantMapper {

    public WorkshopParticipant toDomain(WorkshopParticipantModel model) {
        if (model == null) return null;
        WorkshopParticipant domain = new WorkshopParticipant();
        domain.setId(model.getId());
        if (model.getStudent() != null) domain.setStudentId(model.getStudent().getId());
        if (model.getWorkshop() != null) domain.setWorkshopId(model.getWorkshop().getId());
        return domain;
    }

    public WorkshopParticipantModel toModel(WorkshopParticipant domain, WorkshopModel workshopModel) {
        if (domain == null) return null;
        WorkshopParticipantModel model = new WorkshopParticipantModel();
        model.setId(domain.getId());
        
        StudentModel student = new StudentModel();
        student.setId(domain.getStudentId());
        
        model.setStudent(student);
        model.setWorkshop(workshopModel);

        return model;
    }
}
