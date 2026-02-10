package br.ong.sementesamanha.erp.modules.education.application.dtos.social;

import java.util.List;

public record UpdateFamilyDTO(
    Long domicileTypeId,
    String referenceCras,
    List<CreateFamilyMemberDTO> members
) {}
