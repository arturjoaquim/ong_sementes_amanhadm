package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentNote;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.DailyOccurrenceModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema.UserModel;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

@Component
public class StudentNoteMapper {

    public StudentNote toDomain(DailyOccurrenceModel model) {
        if (model == null) return null;
        StudentNote domain = new StudentNote();
        domain.setId(model.getId());
        domain.setPositive(model.getPositive());
        domain.setShortDescription(model.getSummary());
        domain.setFullDescription(model.getFullDescription());
        if (model.getOccurrenceDate() != null) {
            domain.setNoteDate(Date.from(model.getOccurrenceDate().atZone(ZoneId.systemDefault()).toInstant()));
        }
        if (model.getRegisteredBy() != null) {
            domain.setCreatorUserId(model.getRegisteredBy().getId());
        }
        return domain;
    }

    public DailyOccurrenceModel toModel(StudentNote domain, StudentModel studentModel) {
        if (domain == null) return null;
        DailyOccurrenceModel model = new DailyOccurrenceModel();
        model.setId(domain.getId());
        model.setStudent(studentModel);
        
        // Mapeamento inverso
        model.setPositive(domain.getPositive());
        
        model.setSummary(domain.getShortDescription());
        model.setFullDescription(domain.getFullDescription());
        
        if (domain.getNoteDate() != null) {
            model.setOccurrenceDate(domain.getNoteDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }
        
        if (domain.getCreatorUserId() != null) {
            UserModel user = new UserModel();
            user.setId(domain.getCreatorUserId());
            model.setRegisteredBy(user);
        }
        
        return model;
    }
}
