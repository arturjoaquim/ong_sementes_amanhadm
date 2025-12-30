package br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contatos", schema = "pessoas")
@Getter
@Setter
public class PersonContactModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private IndividualPersonModel person;

    @Column(name = "telefone_fixo")
    private String telephone;

    @Column(name = "celular")
    private String mobilePhone;

    @Column(name = "possui_whatsapp")
    private boolean hasWhatsApp;

    @Column(name = "email")
    private String email;
}
