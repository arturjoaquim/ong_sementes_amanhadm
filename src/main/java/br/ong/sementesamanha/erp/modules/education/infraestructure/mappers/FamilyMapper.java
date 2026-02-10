package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.social.CreateFamilyDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.social.FamilyResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.social.UpdateFamilyDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Family;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class FamilyMapper {

    private final FamilyMemberMapper memberMapper;
    private final FamilyBenefitMapper benefitMapper;
    private final FamilyRiskMapper riskMapper;
    private final HomeVisitMapper homeVisitMapper;

    public FamilyMapper(FamilyMemberMapper memberMapper,
                        FamilyBenefitMapper benefitMapper,
                        FamilyRiskMapper riskMapper,
                        HomeVisitMapper homeVisitMapper) {
        this.memberMapper = memberMapper;
        this.benefitMapper = benefitMapper;
        this.riskMapper = riskMapper;
        this.homeVisitMapper = homeVisitMapper;
    }

    public FamilyResponseDTO toDTO(Family entity) {
        if (entity == null) return null;
        return new FamilyResponseDTO(
            entity.getId(),
            entity.getDomicileTypeId(),
            entity.getFamilyAssessment(),
            entity.getInfoExpirationDate(),
            entity.getReferenceCras(),
            entity.getMembers() != null ? entity.getMembers().stream().map(memberMapper::toDTO).collect(Collectors.toList()) : Collections.emptyList(),
            entity.getBenefits() != null ? entity.getBenefits().stream().map(benefitMapper::toDTO).collect(Collectors.toList()) : Collections.emptyList(),
            entity.getRisks() != null ? entity.getRisks().stream().map(riskMapper::toDTO).collect(Collectors.toList()) : Collections.emptyList(),
            entity.getHomeVisits() != null ? entity.getHomeVisits().stream().map(homeVisitMapper::toDTO).collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    public Family toEntity(CreateFamilyDTO dto) {
        if (dto == null) return null;
        Family entity = new Family();
        entity.setDomicileTypeId(dto.domicileTypeId());
        entity.setReferenceCras(dto.referenceCras());
        
        if (dto.members() != null) {
            entity.setMembers(dto.members().stream()
                    .map(m -> {
                        var member = memberMapper.toEntity(m);
                        member.setFamily(entity);
                        return member;
                    })
                    .collect(Collectors.toList()));
        }
        return entity;
    }

    public void updateEntityFromDto(Family entity, UpdateFamilyDTO dto) {
        if (dto == null || entity == null) return;

        if (dto.domicileTypeId() != null) entity.setDomicileTypeId(dto.domicileTypeId());
        if (dto.referenceCras() != null) entity.setReferenceCras(dto.referenceCras());

        if (dto.members() != null) {
            entity.getMembers().clear();
            entity.getMembers().addAll(dto.members().stream()
                    .map(m -> {
                        var member = memberMapper.toEntity(m);
                        member.setFamily(entity);
                        return member;
                    })
                    .collect(Collectors.toList()));
        }
    }
}
