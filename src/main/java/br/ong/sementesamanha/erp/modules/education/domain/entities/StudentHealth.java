package br.ong.sementesamanha.erp.modules.education.domain.entities;

import java.util.Date;
import java.util.List;

public class StudentHealth {
    private Long id;
    private String ubsName;
    private boolean useGlasses;
    private Date informationExpirationDate;
    private List<StudentMedicalNote> medicalNotes;
}
