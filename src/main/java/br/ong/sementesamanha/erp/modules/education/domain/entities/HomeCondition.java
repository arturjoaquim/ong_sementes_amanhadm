package br.ong.sementesamanha.erp.modules.education.domain.entities;

import br.ong.sementesamanha.erp.modules.education.domain.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "condicoes_domiciliares", schema = "academico")
@Getter
@Setter
public class HomeCondition extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "familia_id")
    private Family family;

    @Column(name = "estado_civil_pais_id")
    private Long parentsMaritalStatusId;

    @Column(name = "mantem_contato_conjuge")
    private Boolean keepsContactWithSpouse;

    @Column(name = "fica_sozinho_casa")
    private Boolean staysAloneAtHome;
}
