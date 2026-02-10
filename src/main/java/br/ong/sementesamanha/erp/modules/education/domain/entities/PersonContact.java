package br.ong.sementesamanha.erp.modules.education.domain.entities;

import br.ong.sementesamanha.erp.modules.education.domain.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contatos", schema = "pessoas")
@Getter
@Setter
public class PersonContact extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private Person person;

    @Column(name = "telefone_fixo")
    private String telephone;

    @Column(name = "celular")
    private String mobilePhone;

    @Column(name = "possui_whatsapp")
    private boolean hasWhatsApp;

    @Column(name = "email")
    private String email;
}
