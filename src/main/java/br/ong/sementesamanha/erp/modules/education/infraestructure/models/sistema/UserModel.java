package br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "usuarios", schema = "sistema")
@Getter
@Setter
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "senha_hash")
    private String passwordHash;

    @Column(name = "ativo")
    private boolean active;

    @Column(name = "criado_em")
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
        name = "usuarios_grupos",
        schema = "sistema",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    private Set<AccessGroupModel> groups;
}
