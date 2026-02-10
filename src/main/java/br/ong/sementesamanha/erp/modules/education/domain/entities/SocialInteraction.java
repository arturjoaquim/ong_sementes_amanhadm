package br.ong.sementesamanha.erp.modules.education.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "interacao_social", schema = "academico")
@Getter
@Setter
public class SocialInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Student student;

    @Column(name = "nivel_interacao")
    private Integer interactionLevel;

    @Column(name = "grupo_social_descricao")
    private String socialGroupDescription;
}
