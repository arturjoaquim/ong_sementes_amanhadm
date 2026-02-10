package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.PersonEducationDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPersonEducation;
import org.springframework.stereotype.Component;

@Component
public class IndividualPersonEducationMapper {

    public IndividualPersonEducation toDomain(PersonEducationDTO dto, IndividualPerson person) {
        if (dto == null) return null;
        IndividualPersonEducation domain = new IndividualPersonEducation();
        domain.setPerson(person);
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

    public PersonEducationDTO toDTO(IndividualPersonEducation domain) {
        if (domain == null) return null;
        return new PersonEducationDTO(
            domain.getInstitution(),
            domain.getPeriodId(),
            domain.getEducationLevelId(),
            domain.getEducationStatusId()
        );
    }
}
