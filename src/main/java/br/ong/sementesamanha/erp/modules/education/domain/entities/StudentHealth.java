package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StudentHealth {
    private Long id;
    private String ubsName;
    private boolean useGlasses;
    private Date informationExpirationDate;
    private List<StudentMedicalNote> medicalNotes;
}
