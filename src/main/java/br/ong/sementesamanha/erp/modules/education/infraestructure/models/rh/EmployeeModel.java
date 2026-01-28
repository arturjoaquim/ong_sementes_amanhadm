package br.ong.sementesamanha.erp.modules.education.infraestructure.models.rh;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.base.Auditable;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.IndividualPersonModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema.UserModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "funcionarios", schema = "rh")
@Getter
@Setter
public class EmployeeModel extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private IndividualPersonModel person;

    @Column(name = "cargo_id")
    private Long positionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_sistema_id")
    private UserModel systemUser;
}
