package br.ong.sementesamanha.erp.modules.education.domain.entities;

import br.ong.sementesamanha.erp.modules.education.domain.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "alunos", schema = "academico")
@Getter
@Setter
public class Student extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private IndividualPerson person;

    @Column(name = "origem_inscricao_id")
    private Long registrationOriginId;

    @Column(name = "periodo_preferencial_id")
    private Long periodId;

    @Column(name = "possui_autonomia_transporte")
    private boolean hasTransportAutonomy;

    @Column(name = "responsavel_transporte")
    private String transportResponsibleName;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_matricula")
    private Date registrationDate;

    @Column(name = "ativo")
    private boolean active;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentGuardian> guardians;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private HomeCondition homeCondition;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private StudentHealth healthRecord;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("occurrenceDate DESC")
    private Set<StudentNote> occurrences;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<SocialInteraction> socialInteractions;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<WorkshopEnrollment> enrollments;

    public double getAttendance() {
       double attendace = 0.0d;

        LocalDateTime registrationDateTime = new java.util.Date(this.getRegistrationDate().getTime())
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        if (this.getEnrollments() != null && !this.getEnrollments().isEmpty()) {
            long totalSessions = this.getEnrollments().stream()
                    .filter(e -> e.getWorkshop() != null && e.getWorkshop().getSessions() != null)
                    .mapToLong(e -> e.getWorkshop().getSessions().stream()
                            .filter(s -> s.getCreatedAt().isAfter(registrationDateTime))
                            .count())
                    .sum();

            long totalPresences = this.getEnrollments().stream()
                    .filter(e -> e.getPresences() != null)
                    .mapToLong(e -> e.getPresences().size())
                    .sum();

            if (totalSessions > 0) {
                attendace = (double) totalPresences / totalSessions * 100;
            }
        }

        return attendace;
    }
}
