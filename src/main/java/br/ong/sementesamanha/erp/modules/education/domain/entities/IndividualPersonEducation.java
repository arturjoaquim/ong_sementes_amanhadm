package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;

@Data
public class IndividualPersonEducation {
    private Long id;
    private String institution;
    private Long periodId;
    private Long educationLevelId;
    private Long educationStatusId;
}
