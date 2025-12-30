package br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "interacao_social", schema = "academico")
@Getter
@Setter
public class SocialInteractionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private StudentModel student;

    @Column(name = "nivel_interacao")
    private Integer interactionLevel;

    @Column(name = "grupo_social_descricao")
    private String socialGroupDescription;
}
