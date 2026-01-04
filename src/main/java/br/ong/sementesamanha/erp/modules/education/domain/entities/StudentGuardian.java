package br.ong.sementesamanha.erp.modules.education.domain.entities;

import lombok.Data;

@Data
public class StudentGuardian {
    private Long studentId;
    private Long guardianId;
    private Long kinshipId;
}
