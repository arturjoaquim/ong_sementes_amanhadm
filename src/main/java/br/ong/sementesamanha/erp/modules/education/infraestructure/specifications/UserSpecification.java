package br.ong.sementesamanha.erp.modules.education.infraestructure.specifications;

import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> withLoginAndGroups(String login) {
        return (root, query, cb) -> {
            // A verificação do tipo de resultado previne que o fetch seja aplicado em queries de contagem.
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                root.fetch("groups", JoinType.LEFT);
            }
            return cb.equal(root.get("login"), login);
        };
    }
}
