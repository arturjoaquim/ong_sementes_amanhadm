package br.ong.sementesamanha.erp.modules.education.application.dtos.workshop;

import java.util.List;

public record UpdatePresencesDTO(
    List<Long> studentIds
) {}
