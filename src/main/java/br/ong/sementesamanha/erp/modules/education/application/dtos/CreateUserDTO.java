package br.ong.sementesamanha.erp.modules.education.application.dtos;

import java.util.Set;

public record CreateUserDTO(
    String login,
    String password,
    Set<Long> groupIds
) {}
