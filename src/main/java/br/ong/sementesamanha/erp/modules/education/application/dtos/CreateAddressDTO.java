package br.ong.sementesamanha.erp.modules.education.application.dtos;

public record CreateAddressDTO(
    String cep,
    String streetNumber,
    String street,
    String neighborhood,
    String city,
    String complement,
    String uf
) {}
