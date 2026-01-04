package br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cadastro", schema = "pessoas")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonModel extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_pessoa")
    private Integer personType;
}
