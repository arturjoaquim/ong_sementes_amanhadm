package br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.social.FamilyModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "condicoes_domiciliares", schema = "academico")
@Getter
@Setter
public class HomeConditionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private StudentModel student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familia_id")
    private FamilyModel family;

    @Column(name = "estado_civil_pais_id")
    private Long parentsMaritalStatusId;

    @Column(name = "mantem_contato_conjuge")
    private Boolean keepsContactWithSpouse;

    @Column(name = "fica_sozinho_casa")
    private Boolean staysAloneAtHome;
}
