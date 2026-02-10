package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.StudentGuardianResponseDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentGuardian;
import org.springframework.stereotype.Component;

@Component
public class StudentGuardianMapper {

    private IndividualPersonMapper personMapper;

    public StudentGuardianMapper (IndividualPersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public StudentGuardianResponseDTO toDTO(StudentGuardian domain) {
        StudentGuardianResponseDTO dto = new StudentGuardianResponseDTO();
        dto.setId(domain.getId().getGuardianId());
        dto.setPerson(personMapper.toDTO(domain.getGuardian().getPerson()));
        dto.setKinshipId(domain.getKinshipId());
        return dto;
    }
}
