package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.person.PersonDocumentDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Person;
import br.ong.sementesamanha.erp.modules.education.domain.entities.PersonDocument;
import br.ong.sementesamanha.erp.modules.education.domain.vos.DocumentDetail;
import br.ong.sementesamanha.erp.modules.education.infraestructure.enums.DocumentTypeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PersonDocumentMapper {

    private final ObjectMapper objectMapper;

    public PersonDocumentMapper() {
        this.objectMapper = new ObjectMapper();
    }

    public PersonDocument toDomain(PersonDocumentDTO dto, Person person) {
        if (dto == null) return null;
        PersonDocument domain = new PersonDocument();
        domain.setPerson(person);
        domain.setDocumentTypeId(dto.documentTypeId());
        domain.setNumber(dto.number());
        domain.setActive(dto.active());

        if (dto.extraData() != null && dto.documentTypeId() != null) {
            try {
                DocumentTypeEnum type = DocumentTypeEnum.fromId(dto.documentTypeId());
                DocumentDetail detail = objectMapper.convertValue(dto.extraData(), type.getDetailClass());
                Map<String, Object> json = objectMapper.convertValue(detail, Map.class);
                domain.setDocumentDetail(json);
            } catch (Exception e) {
                domain.setDocumentDetail(null);
            }
        }
        return domain;
    }

    public void updateDomainFromDto(PersonDocument domain, PersonDocumentDTO dto) {
        if (dto == null || domain == null) return;

        if (dto.documentTypeId() != null) domain.setDocumentTypeId(dto.documentTypeId());
        if (dto.number() != null) domain.setNumber(dto.number());
        if (dto.active() != null) domain.setActive(dto.active());

        if (dto.extraData() != null && domain.getDocumentTypeId() != null) {
            try {
                DocumentTypeEnum type = DocumentTypeEnum.fromId(domain.getDocumentTypeId());
                DocumentDetail detail = objectMapper.convertValue(dto.extraData(), type.getDetailClass());
                Map<String, Object> json = objectMapper.convertValue(detail, Map.class);
                domain.setDocumentDetail(json);
            } catch (Exception e) {
                domain.setDocumentDetail(null);
            }
        }
    }

    public PersonDocumentDTO toDTO(PersonDocument domain) {
        if (domain == null) return null;
        return new PersonDocumentDTO(
            domain.getId(),
            domain.getDocumentTypeId(),
            domain.getNumber(),
            domain.getDocumentDetail(),
            domain.getActive()
        );
    }
}
