package br.ong.sementesamanha.erp.modules.education.application.dtos;

import java.util.Map;

public record CreateDocumentDTO(
    Long documentTypeId,
    String number,
    Map<String, Object> extraData
) {}
