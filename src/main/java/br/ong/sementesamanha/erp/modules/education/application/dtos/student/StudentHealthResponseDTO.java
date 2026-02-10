package br.ong.sementesamanha.erp.modules.education.application.dtos.student;

import java.time.LocalDate;
import java.util.List;

public record StudentHealthResponseDTO(
    Long id,
    String ubsReference,
    Boolean wearsGlasses,
    LocalDate infoExpirationDate,
    List<StudentMedicationResponseDTO> medications,
    List<MedicalTreatmentResponseDTO> treatments
) {}
