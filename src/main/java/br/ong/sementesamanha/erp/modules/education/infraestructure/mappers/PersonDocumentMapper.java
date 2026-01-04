package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateDocumentDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonDocument;
import br.ong.sementesamanha.erp.modules.education.infraestructure.enums.DocumentTypeEnum;
import br.ong.sementesamanha.erp.modules.education.domain.vos.DocumentDetail;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.PersonDocumentModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PersonDocumentMapper {

    private final ObjectMapper objectMapper;

    public PersonDocumentMapper() {
        this.objectMapper = new ObjectMapper();
    }

    public PersonDocument toDomain(CreateDocumentDTO dto) {
        PersonDocument document = new PersonDocument();
        document.setDocumentTypeId(dto.documentTypeId());
        document.setNumber(dto.number());

        if (dto.extraData() != null && dto.documentTypeId() != null) {
            try {
                DocumentTypeEnum type = DocumentTypeEnum.fromId(dto.documentTypeId());
                DocumentDetail detail = objectMapper.convertValue(dto.extraData(), type.getDetailClass());
                document.setDocumentDetail(detail);
            } catch (Exception e) {
                // Logar erro ou tratar
                document.setDocumentDetail(null);
            }
        }
        return document;
    }

    public PersonDocument toDomain(PersonDocumentModel model) {
        if (model == null) return null;
        PersonDocument domain = new PersonDocument();
        domain.setId(model.getId());
        domain.setDocumentTypeId(model.getDocumentTypeId());
        domain.setNumber(model.getNumber());
        
        if (model.getExtraData() != null && model.getDocumentTypeId() != null) {
            try {
                DocumentTypeEnum type = DocumentTypeEnum.fromId(model.getDocumentTypeId());
                DocumentDetail detail = objectMapper.convertValue(model.getExtraData(), type.getDetailClass());
                domain.setDocumentDetail(detail);
            } catch (Exception e) {
                // Logar erro, mas não quebrar a aplicação
                domain.setDocumentDetail(null);
            }
        }
        
        return domain;
    }

    public PersonDocumentModel toModel(PersonDocument domain) {
        if (domain == null) return null;
        PersonDocumentModel model = new PersonDocumentModel();
        model.setId(domain.getId());
        model.setDocumentTypeId(domain.getDocumentTypeId());
        model.setNumber(domain.getNumber());
        
        if (domain.getDocumentDetail() != null) {
            try {
                Map<String, Object> json = objectMapper.convertValue(domain.getDocumentDetail(), Map.class);
                model.setExtraData(json);
            } catch (Exception e) {
                model.setExtraData(null);
            }
        }

        return model;
    }
}
