package br.ong.sementesamanha.erp.modules.education.application.dtos.person;

import java.util.Map;

public record PersonDocumentDTO(
    Long id,
    Long documentTypeId,
    String number,
    Map<String, Object> extraData,
    Boolean active
) {}
