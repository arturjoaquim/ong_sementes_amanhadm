package br.ong.sementesamanha.erp.modules.education.application.dtos.person;

public record PersonAddressDTO(
    String cep,
    String streetNumber,
    String street,
    String neighborhood,
    String city,
    String complement,
    String uf
) {}
