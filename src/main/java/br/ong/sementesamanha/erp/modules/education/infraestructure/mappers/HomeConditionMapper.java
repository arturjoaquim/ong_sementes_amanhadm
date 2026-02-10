package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.CreateHomeConditionDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.HomeConditionResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.student.UpdateHomeConditionDTO;
import br.ong.sementesamanha.erp.modules.education.application.services.LookupService;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Family;
import br.ong.sementesamanha.erp.modules.education.domain.entities.HomeCondition;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class HomeConditionMapper {

    private final LookupService lookupService;
    private final FamilyMapper familyMapper;

    public HomeConditionMapper(LookupService lookupService, FamilyMapper familyMapper) {
        this.lookupService = lookupService;
        this.familyMapper = familyMapper;
    }

    public HomeConditionResponseDTO toDTO(HomeCondition entity) {
        if (entity == null) return null;

        return new HomeConditionResponseDTO(
            entity.getId(),
            entity.getFamily() != null ? familyMapper.toDTO(entity.getFamily()) : null,
            entity.getParentsMaritalStatusId(),
            entity.getKeepsContactWithSpouse(),
            entity.getStaysAloneAtHome()
        );
    }

    public HomeCondition toEntity(CreateHomeConditionDTO dto, Student student) {
        if (dto == null) return null;
        HomeCondition entity = new HomeCondition();
        entity.setStudent(student);
        entity.setParentsMaritalStatusId(dto.parentsMaritalStatusId());
        entity.setKeepsContactWithSpouse(dto.keepsContactWithSpouse());
        entity.setStaysAloneAtHome(dto.staysAloneAtHome());
        
        if (dto.family() != null) {
            entity.setFamily(familyMapper.toEntity(dto.family()));
        }
        
        return entity;
    }

    public void updateEntityFromDto(HomeCondition entity, UpdateHomeConditionDTO dto) {
        if (dto == null || entity == null) return;

        if (dto.parentsMaritalStatusId() != null) entity.setParentsMaritalStatusId(dto.parentsMaritalStatusId());
        if (dto.keepsContactWithSpouse() != null) entity.setKeepsContactWithSpouse(dto.keepsContactWithSpouse());
        if (dto.staysAloneAtHome() != null) entity.setStaysAloneAtHome(dto.staysAloneAtHome());
        
        if (dto.family() != null) {
            if (entity.getFamily() == null) {
                Family newFamily = new Family();
                entity.setFamily(newFamily);
            }
            familyMapper.updateEntityFromDto(entity.getFamily(), dto.family());
        }
    }
}
