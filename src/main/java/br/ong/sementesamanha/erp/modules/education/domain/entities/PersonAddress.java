package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;

@Data
public class PersonAddress {
    private Long id;
    private String cep;
    private String streetNumber;
    private String street;
    private String neighborhood;
    private String city;
    private String complement;
    private String uf;
}
