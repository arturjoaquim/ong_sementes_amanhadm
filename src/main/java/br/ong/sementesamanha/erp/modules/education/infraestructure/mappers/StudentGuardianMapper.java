package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentGuardian;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.LegalGuardianModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentGuardianId;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentGuardianModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentModel;
import org.springframework.stereotype.Component;

@Component
public class StudentGuardianMapper {

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
}
