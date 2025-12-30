package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;

@Data
public abstract class Person {
    private Long id;
    private Long personTypeId;
}
