package br.ong.sementesamanha.erp.modules.education.domain.vos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CpfDetail extends DocumentDetail {
    // CPF geralmente não tem dados extras além do número, mas mantemos a classe para consistência
}
