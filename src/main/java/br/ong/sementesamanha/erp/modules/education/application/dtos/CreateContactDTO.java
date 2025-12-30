package br.ong.sementesamanha.erp.modules.education.application.dtos;

public record CreateContactDTO(
    String telephone,
    String mobilePhone,
    boolean hasWhatsApp,
    String email
) {}
