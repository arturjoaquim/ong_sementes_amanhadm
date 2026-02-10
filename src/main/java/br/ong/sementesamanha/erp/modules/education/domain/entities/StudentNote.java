package br.ong.sementesamanha.erp.modules.education.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "ocorrencias_diarias", schema = "academico")
@Getter
@Setter
public class StudentNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Student student;

    @Column(name = "is_positivo")
    private Boolean positive;

    @Column(name = "resumo")
    private String summary;

    @Column(name = "descricao_completa")
    private String fullDescription;

    @Column(name = "data_ocorrencia")
    private LocalDateTime occurrenceDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registrado_por_id")
    private User registeredBy;
}
