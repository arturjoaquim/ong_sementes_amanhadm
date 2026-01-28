package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;

@Data
public class Employee {
    private Long id;
    private Long personId;
    private Long positionId;
    private Long systemUserId;
}
