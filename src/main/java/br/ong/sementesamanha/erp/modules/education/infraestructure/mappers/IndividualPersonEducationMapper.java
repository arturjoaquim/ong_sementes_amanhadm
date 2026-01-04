package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateEducationDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPersonEducation;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.IndividualPersonEducationModel;
import org.springframework.stereotype.Component;

@Component
public class IndividualPersonEducationMapper {

    public IndividualPersonEducation toDomain(IndividualPersonEducationModel model) {
        if (model == null) return null;
        IndividualPersonEducation domain = new IndividualPersonEducation();
        domain.setId(model.getId());
        domain.setInstitution(model.getInstitution());
        domain.setPeriodId(model.getPeriodId());
        domain.setEducationLevelId(model.getEducationLevelId());
        domain.setEducationStatusId(model.getEducationStatusId());
        return domain;
    }

    public IndividualPersonEducation toDomain(CreateEducationDTO dto) {
        IndividualPersonEducation education = new IndividualPersonEducation();
        education.setInstitution(dto.institution());
        education.setPeriodId(dto.periodId());
        education.setEducationLevelId(dto.educationLevelId());
        education.setEducationStatusId(dto.educationStatusId());
        return education;
    }

    public IndividualPersonEducationModel toModel(IndividualPersonEducation domain) {
        if (domain == null) return null;
        IndividualPersonEducationModel model = new IndividualPersonEducationModel();
        model.setId(domain.getId());
        model.setInstitution(domain.getInstitution());
        model.setPeriodId(domain.getPeriodId());
        model.setEducationLevelId(domain.getEducationLevelId());
        model.setEducationStatusId(domain.getEducationStatusId());
        return model;
    }
}
