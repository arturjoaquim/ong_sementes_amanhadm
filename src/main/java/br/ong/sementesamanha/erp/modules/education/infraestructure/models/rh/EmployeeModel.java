package br.ong.sementesamanha.erp.modules.education.infraestructure.models.rh;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.PersonModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema.UserModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "funcionarios", schema = "rh")
@Getter
@Setter
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private PersonModel person;

    @Column(name = "cargo_id")
    private Long positionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_sistema_id")
    private UserModel systemUser;
}
