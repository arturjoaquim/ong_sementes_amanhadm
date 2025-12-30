package br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "cadastro", schema = "pessoas")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_pessoa")
    private Integer personType;

    @Column(name = "data_cadastro")
    private LocalDateTime registrationDate;
}
