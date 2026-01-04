package br.ong.sementesamanha.erp.modules.education.domain.entities;

import br.ong.sementesamanha.erp.modules.education.domain.vos.DocumentDetail;
import lombok.Data;

@Data
public class PersonDocument {
    private Long id;
    private Long documentTypeId;
    private String number;
    private DocumentDetail documentDetail;
}
