package br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "documentos", schema = "pessoas")
@Getter
@Setter
public class PersonDocumentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private IndividualPersonModel person;

    @Column(name = "tipo_documento_id")
    private Long documentTypeId;

    @Column(name = "numero")
    private String number;
    
    @Column(name = "dados_extras", columnDefinition = "jsonb")
    private String extraData;
}
