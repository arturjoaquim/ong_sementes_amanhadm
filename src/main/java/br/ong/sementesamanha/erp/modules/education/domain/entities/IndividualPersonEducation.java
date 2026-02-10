package br.ong.sementesamanha.erp.modules.education.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "escolaridade", schema = "pessoas")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class IndividualPersonEducation {
    @Id
    @Column(name = "pessoa_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "pessoa_id")
    private IndividualPerson person;

    @Column(name = "instituicao_nome")
    private String institution;

    @Column(name = "periodo_id")
    private Long periodId;

    @Column(name = "nivel_ensino_id")
    private Long educationLevelId;

    @Column(name = "status_ensino_id")
    private Long educationStatusId;

    @LastModifiedDate
    @Column(name = "atualizado_em")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "atualizado_por_id")
    private Long updatedBy;
}
