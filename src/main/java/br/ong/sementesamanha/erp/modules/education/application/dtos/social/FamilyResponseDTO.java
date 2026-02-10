package br.ong.sementesamanha.erp.modules.education.application.dtos.social;

import java.time.LocalDate;
import java.util.List;

public record FamilyResponseDTO(
    Long id,
    Long domicileTypeId,
    String familyAssessment,
    LocalDate infoExpirationDate,
    String referenceCras,
    List<FamilyMemberResponseDTO> members,
    List<FamilyBenefitResponseDTO> benefits,
    List<FamilyRiskResponseDTO> risks,
    List<HomeVisitResponseDTO> homeVisits
) {}
