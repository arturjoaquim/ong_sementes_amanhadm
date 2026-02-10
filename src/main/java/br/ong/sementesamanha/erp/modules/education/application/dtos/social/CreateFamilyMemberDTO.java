package br.ong.sementesamanha.erp.modules.education.application.dtos.social;

import java.math.BigDecimal;

public record CreateFamilyMemberDTO(
    String name,
    String profession,
    BigDecimal monthlyIncome,
    Long kinshipId
) {}
