package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.user.AccessGroupDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.AccessGroup;
import org.springframework.stereotype.Component;

@Component
public class AccessGroupMapper {

    public AccessGroupDTO toDTO(AccessGroup entity) {
        if (entity == null) return null;
        return new AccessGroupDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }

    public AccessGroup toEntity(AccessGroupDTO dto) {
        if (dto == null) return null;
        AccessGroup entity = new AccessGroup();
        entity.setId(dto.id());
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        return entity;
    }
}
