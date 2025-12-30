package br.ong.sementesamanha.erp.modules.education.application.dtos;

import java.util.Date;
import java.util.List;

public record CreatePersonDTO(
    String personName,
    Date birthDate,
    String motherName,
    String fatherName,
    Long naturalnessId,
    Long raceId,
    Long sexId,
    CreateAddressDTO address,
    CreateContactDTO contact,
    CreateEducationDTO education,
    List<CreateDocumentDTO> documents
) {}
