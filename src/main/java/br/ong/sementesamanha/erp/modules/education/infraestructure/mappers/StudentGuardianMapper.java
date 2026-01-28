package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentGuardian;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.LegalGuardianModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentGuardianId;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentGuardianModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentModel;
import org.springframework.stereotype.Component;

@Component
public class StudentGuardianMapper {

    private final LegalGuardianMapper legalGuardianMapper;

    public StudentGuardianMapper(LegalGuardianMapper legalGuardianMapper) {
        this.legalGuardianMapper = legalGuardianMapper;
    }

    public StudentGuardianModel toModel(StudentGuardian domain, StudentModel studentModel) {
        if (domain == null) return null;

        StudentGuardianModel model = new StudentGuardianModel();
        
        LegalGuardianModel guardianModel = new LegalGuardianModel();
        guardianModel.setId(domain.getGuardianId());

        model.setId(new StudentGuardianId(studentModel.getId(), guardianModel.getId()));
        model.setStudent(studentModel);
        model.setGuardian(guardianModel);
        model.setKinshipId(domain.getKinshipId());

        return model;
    }

    public StudentGuardian toDomain(StudentGuardianModel model) {
        if (model == null) return null;

        StudentGuardian domain = new StudentGuardian();
        domain.setStudentId(model.getId().getStudentId());
        domain.setGuardianId(model.getId().getGuardianId());
        domain.setKinshipId(model.getKinshipId());
        
        if (model.getGuardian() != null) {
            domain.setGuardian(legalGuardianMapper.toDomain(model.getGuardian()));
        }

        return domain;
    }
}
