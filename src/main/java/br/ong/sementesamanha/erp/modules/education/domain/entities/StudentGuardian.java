package br.ong.sementesamanha.erp.modules.education.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "alunos_responsaveis", schema = "academico")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class StudentGuardian {

    @EmbeddedId
    private StudentGuardianId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "aluno_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("guardianId")
    @JoinColumn(name = "responsavel_id")
    private LegalGuardian guardian;

    @Column(name = "parentesco_id")
    private Long kinshipId;

    @Column(name = "criado_em")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "criado_por_id")
    @CreatedBy
    private Long createdBy;
}
