package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.social.CreateFamilyMemberDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.social.FamilyMemberResponseDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.FamilyMember;
import org.springframework.stereotype.Component;

@Component
public class FamilyMemberMapper {

    public FamilyMemberResponseDTO toDTO(FamilyMember entity) {
        if (entity == null) return null;
        return new FamilyMemberResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getProfession(),
            entity.getMonthlyIncome(),
            entity.getKinshipId(),
            entity.isActive()
        );
    }

    public FamilyMember toEntity(CreateFamilyMemberDTO dto) {
        if (dto == null) return null;
        FamilyMember entity = new FamilyMember();
        entity.setName(dto.name());
        entity.setProfession(dto.profession());
        entity.setMonthlyIncome(dto.monthlyIncome());
        entity.setKinshipId(dto.kinshipId());
        entity.setActive(true);
        return entity;
    }
}
