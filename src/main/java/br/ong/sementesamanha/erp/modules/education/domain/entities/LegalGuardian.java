package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;

@Data
public class LegalGuardian {
    private Long id;
    private Long personId;
    private IndividualPerson person;
    // O parentesco fica na tabela de junção (StudentGuardian), mas aqui representamos o responsável em si.
}
