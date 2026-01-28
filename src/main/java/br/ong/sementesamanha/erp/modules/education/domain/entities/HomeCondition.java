package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;

@Data
public class HomeCondition {
    private Long id;
    private Long familyId;
    private Long parentsMaritalStatusId;
    private Boolean contactWithSpouse;
    private Boolean staysAlone;
}
