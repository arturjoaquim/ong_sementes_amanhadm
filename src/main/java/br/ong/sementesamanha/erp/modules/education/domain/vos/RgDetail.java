package br.ong.sementesamanha.erp.modules.education.domain.vos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class RgDetail extends DocumentDetail {
    private String issuingAgency;
    private LocalDate issueDate;
    private String state;
}
