package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateUserDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.UserRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema.AccessGroupModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.AccessGroupJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccessGroupJpaRepository accessGroupRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, 
                       AccessGroupJpaRepository accessGroupRepository, 
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accessGroupRepository = accessGroupRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User create(CreateUserDTO dto) {
        if (userRepository.findByLogin(dto.login()).isPresent()) {
            throw new IllegalArgumentException("Login já está em uso.");
        }

        User user = new User();
        user.setLogin(dto.login());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setActive(true);

        if (dto.groupIds() != null && !dto.groupIds().isEmpty()) {
            Set<AccessGroupModel> groups = new HashSet<>(accessGroupRepository.findAllById(dto.groupIds()));
            user.setRoles(groups.stream().map(AccessGroupModel::getName).collect(Collectors.toSet()));
        }

        return userRepository.save(user);
    }
}
