package br.ong.sementesamanha.erp.modules.education.application.dtos.person;

import java.util.Date;
import java.util.List;

public record IndividualPersonDTO(
    Long id,
    String personName,
    Date birthDate,
    String motherName,
    String fatherName,
    Long naturalnessId,
    Long raceId,
    Long sexId,
    PersonAddressDTO address,
    PersonContactDTO contact,
    PersonEducationDTO education,
    List<PersonDocumentDTO> documents
) {}
