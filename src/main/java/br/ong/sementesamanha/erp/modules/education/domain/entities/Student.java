package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    private Long personId;
    private Long registrationOriginId;
    private Long periodId;
    private boolean hasTransportAutonomy;
    private String transportResponsibleName;
    private Date registrationDate;
    private boolean active;
    private List<StudentNote> notes;
    private List<StudentGuardian> guardians;
}
