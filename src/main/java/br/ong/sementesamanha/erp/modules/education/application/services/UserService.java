package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateUserDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.AccessGroup;
import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.AccessGroupRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccessGroupRepository accessGroupRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, 
                       AccessGroupRepository accessGroupRepository, 
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
        user.setPasswordHash(passwordEncoder.encode(dto.password()));
        user.setActive(true);

        if (dto.groupIds() != null && !dto.groupIds().isEmpty()) {
            Set<AccessGroup> groups = new HashSet<>(accessGroupRepository.findAllById(dto.groupIds()));
            user.setGroups(groups);
        }

        return userRepository.save(user);
    }
}
