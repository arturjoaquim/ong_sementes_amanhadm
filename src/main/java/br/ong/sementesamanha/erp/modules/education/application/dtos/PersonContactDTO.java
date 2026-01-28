package br.ong.sementesamanha.erp.modules.education.application.dtos;

public record PersonContactDTO(
    String telephone,
    String mobilePhone,
    Boolean hasWhatsApp,
    String email
) {}
