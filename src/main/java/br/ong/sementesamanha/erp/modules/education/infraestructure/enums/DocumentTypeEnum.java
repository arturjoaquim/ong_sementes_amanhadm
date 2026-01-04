package br.ong.sementesamanha.erp.modules.education.infraestructure.enums;

import br.ong.sementesamanha.erp.modules.education.domain.vos.CertidaoNascimentoDetail;
import br.ong.sementesamanha.erp.modules.education.domain.vos.CpfDetail;
import br.ong.sementesamanha.erp.modules.education.domain.vos.DocumentDetail;
import br.ong.sementesamanha.erp.modules.education.domain.vos.RgDetail;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DocumentTypeEnum {
    CPF(1L, CpfDetail.class),
    RG(2L, RgDetail.class),
    CERTIDAO_NASCIMENTO(3L, CertidaoNascimentoDetail.class);

    private final Long id;
    private final Class<? extends DocumentDetail> detailClass;

    DocumentTypeEnum(Long id, Class<? extends DocumentDetail> detailClass) {
        this.id = id;
        this.detailClass = detailClass;
    }

    public static DocumentTypeEnum fromId(Long id) {
        return Arrays.stream(values())
                .filter(type -> type.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Document Type ID: " + id));
    }
}
