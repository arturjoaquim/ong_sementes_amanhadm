package br.ong.sementesamanha.erp.modules.education.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "fisicas", schema = "pessoas")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class IndividualPerson {
    @Id
    @Column(name = "pessoa_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId
    @JoinColumn(name = "pessoa_id")
    private Person basePerson;

    @Column(name = "nome")
    private String personName;

    @Column(name = "naturalidade_id")
    private Long naturalnessId;

    @Column(name = "raca_id")
    private Long raceId;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date birthDate;

    @Column(name = "sexo_id")
    private Long sexId;

    @Column(name = "nome_mae")
    private String motherName;

    @Column(name = "nome_pai")
    private String fatherName;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private IndividualPersonEducation education;

    @LastModifiedDate
    @Column(name = "atualizado_em")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "atualizado_por_id")
    private Long updatedBy;
}
