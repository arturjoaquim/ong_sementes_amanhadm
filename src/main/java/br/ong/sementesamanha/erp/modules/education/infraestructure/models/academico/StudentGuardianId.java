package br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGuardianId implements Serializable {
    @Column(name = "aluno_id")
    private Long studentId;

    @Column(name = "responsavel_id")
    private Long guardianId;
}
