package br.ong.sementesamanha.erp.modules.education.domain.vos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CertidaoNascimentoDetail extends DocumentDetail {
    private String book;
    private String sheet;
    private String term;
}
