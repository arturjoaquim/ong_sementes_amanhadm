package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema.AccessGroupModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema.UserModel;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toDomain(UserModel model) {
        if (model == null) return null;
        User user = new User();
        user.setId(model.getId());
        user.setLogin(model.getLogin());
        user.setPassword(model.getPasswordHash());
        user.setActive(model.isActive());
        
        if (model.getGroups() != null) {
            user.setRoles(model.getGroups().stream()
                    .map(AccessGroupModel::getName)
                    .collect(Collectors.toSet()));
        }
        
        return user;
    }

    public UserModel toModel(User domain) {
        if (domain == null) return null;
        UserModel model = new UserModel();
        model.setId(domain.getId());
        model.setLogin(domain.getLogin());
        model.setPasswordHash(domain.getPassword());
        model.setActive(domain.isActive());
        // Mapeamento reverso de roles para groups exigiria buscar os grupos no banco
        // O UserService cuida disso na criação.
        return model;
    }
}
