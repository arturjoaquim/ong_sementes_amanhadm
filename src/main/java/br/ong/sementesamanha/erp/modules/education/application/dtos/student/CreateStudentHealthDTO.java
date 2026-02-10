package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

import java.time.LocalDate;
import java.util.List;

public record CreateStudentHealthDTO(
    Long studentId,
    String ubsReference,
    Boolean wearsGlasses,
    LocalDate infoExpirationDate,
    List<CreateStudentMedicationDTO> medications,
    List<CreateMedicalTreatmentDTO> treatments
) {}
