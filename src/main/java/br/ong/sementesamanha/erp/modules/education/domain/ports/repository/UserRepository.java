package br.ong.sementesamanha.erp.modules.education.domain.ports.repository;

import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByLogin(String login);
    Optional<User> findByLoginWithRoles(String login);
    User save(User user);
}
