package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.user.UserDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final AccessGroupMapper accessGroupMapper;

    public UserMapper(AccessGroupMapper accessGroupMapper) {
        this.accessGroupMapper = accessGroupMapper;
    }

    public UserDTO toDTO(User entity) {
        if (entity == null) return null;
        return new UserDTO(
                entity.getId(),
                entity.getLogin(),
                null, // Não retornamos a senha no DTO por segurança
                entity.getGroups() != null ? entity.getGroups().stream()
                        .map(accessGroupMapper::toDTO)
                        .collect(Collectors.toList()) : null
        );
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) return null;
        User entity = new User();
        entity.setId(dto.id());
        entity.setLogin(dto.username());
        entity.setPasswordHash(dto.password());
        // Groups devem ser carregados/gerenciados pelo serviço
        return entity;
    }
}
