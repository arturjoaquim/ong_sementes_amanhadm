package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class IndividualPerson extends Person {
    private String personName;
    private Long naturalnessId;
    private Long raceId;
    private Date birthDate;
    private Long sexId;
    private String motherName;
    private String fatherName;
    private PersonAddress address;
    private PersonContact contact;
    private IndividualPersonEducation education;
    private List<PersonDocument> documents;
}
