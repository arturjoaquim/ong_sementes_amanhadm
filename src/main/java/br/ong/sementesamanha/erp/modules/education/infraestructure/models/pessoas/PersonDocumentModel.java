package br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "documentos", schema = "pessoas")
@Getter
@Setter
public class PersonDocumentModel extends Auditable {
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
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "dados_extras", columnDefinition = "jsonb")
    private Map<String, Object> extraData;

    @Column(name="ativo")
    private Boolean active;
}
