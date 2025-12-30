package br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "grupos_acesso", schema = "sistema")
@Getter
@Setter
public class AccessGroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    @Column(name = "criado_em")
    private LocalDateTime createdAt;
}
