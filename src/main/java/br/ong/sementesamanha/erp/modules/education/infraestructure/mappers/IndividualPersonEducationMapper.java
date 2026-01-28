package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.PersonEducationDTO;
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

    public IndividualPersonEducationModel toModel(IndividualPersonEducation domain) {
        if (domain == null) return null;
        IndividualPersonEducationModel model = new IndividualPersonEducationModel();
        updateModelFromDomain(model, domain);
        return model;
    }

    public void updateModelFromDomain(IndividualPersonEducationModel model, IndividualPersonEducation domain) {
        if (domain == null || model == null) return;
        model.setId(domain.getId());
        model.setInstitution(domain.getInstitution());
        model.setPeriodId(domain.getPeriodId());
        model.setEducationLevelId(domain.getEducationLevelId());
        model.setEducationStatusId(domain.getEducationStatusId());
    }

    public IndividualPersonEducation toDomain(PersonEducationDTO dto) {
        if (dto == null) return null;
        IndividualPersonEducation domain = new IndividualPersonEducation();
        domain.setInstitution(dto.institution());
        domain.setPeriodId(dto.periodId());
        domain.setEducationLevelId(dto.educationLevelId());
        domain.setEducationStatusId(dto.educationStatusId());
        return domain;
    }

    public void updateDomainFromDto(IndividualPersonEducation domain, PersonEducationDTO dto) {
        if (dto == null || domain == null) return;

        if (dto.institution() != null) domain.setInstitution(dto.institution());
        if (dto.periodId() != null) domain.setPeriodId(dto.periodId());
        if (dto.educationLevelId() != null) domain.setEducationLevelId(dto.educationLevelId());
        if (dto.educationStatusId() != null) domain.setEducationStatusId(dto.educationStatusId());
    }
}
