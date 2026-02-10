package br.ong.sementesamanha.erp.modules.education.application.dtos.social;

import java.math.BigDecimal;

public record UpdateFamilyMemberDTO(
    String name,
    String profession,
    BigDecimal monthlyIncome,
    Long kinshipId
) {}
