package br.ong.sementesamanha.erp.modules.education.application.dtos.social;

import java.math.BigDecimal;

public record FamilyMemberResponseDTO(
    Long id,
    String name,
    String profession,
    BigDecimal monthlyIncome,
    Long kinshipId,
    boolean active
) {}
