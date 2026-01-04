package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.adapter;

import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.UserRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.UserMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema.AccessGroupModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema.UserModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.AccessGroupJpaRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.UserJpaRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.specifications.UserSpecification;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class UserJpaAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;
    private final AccessGroupJpaRepository accessGroupRepository;
    private final UserMapper mapper;

    public UserJpaAdapter(UserJpaRepository jpaRepository, 
                          AccessGroupJpaRepository accessGroupRepository,
                          UserMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.accessGroupRepository = accessGroupRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return jpaRepository.findByLogin(login)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByLoginWithRoles(String login) {
        return jpaRepository.findOne(UserSpecification.withLoginAndGroups(login))
                .map(mapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserModel model = mapper.toModel(user);
        
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            Set<AccessGroupModel> groups = new HashSet<>();
            for (String roleName : user.getRoles()) {
                accessGroupRepository.findByName(roleName).ifPresent(groups::add);
            }
            model.setGroups(groups);
        }

        UserModel saved = jpaRepository.save(model);
        return mapper.toDomain(saved);
    }
}
