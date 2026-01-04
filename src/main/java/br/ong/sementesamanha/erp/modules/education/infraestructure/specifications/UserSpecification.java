package br.ong.sementesamanha.erp.modules.education.infraestructure.specifications;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema.UserModel;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<UserModel> withLoginAndGroups(String login) {
        return (root, query, cb) -> {
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                root.fetch("groups", JoinType.LEFT);
            }
            return cb.equal(root.get("login"), login);
        };
    }
}
