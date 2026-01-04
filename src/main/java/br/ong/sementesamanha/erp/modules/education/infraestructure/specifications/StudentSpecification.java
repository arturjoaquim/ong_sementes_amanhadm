package br.ong.sementesamanha.erp.modules.education.infraestructure.specifications;

import br.ong.sementesamanha.erp.modules.education.domain.filters.StudentFilter;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.LegalGuardianModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentGuardianModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.IndividualPersonModel;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StudentSpecification {

    public static Specification<StudentModel> withFilter(StudentFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getStudentName() != null && !filter.getStudentName().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("person").get("personName")), 
                        "%" + filter.getStudentName().toLowerCase() + "%"
                ));
            }

            if (filter.getGuardianName() != null && !filter.getGuardianName().isEmpty()) {
                 predicates.add(criteriaBuilder.like(
                         criteriaBuilder.lower(root.get("transportResponsibleName")), 
                         "%" + filter.getGuardianName().toLowerCase() + "%"
                 ));
            }

            if (filter.getStatus() != null && !filter.getStatus().isEmpty()) {
                boolean isActive = "ativo".equalsIgnoreCase(filter.getStatus());
                predicates.add(criteriaBuilder.equal(root.get("active"), isActive));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<StudentModel> fetchForPreview() {
        return (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                query.distinct(true);

                // Fetch da pessoa e seus relacionamentos OneToOne para evitar N+1
                Fetch<StudentModel, IndividualPersonModel> personFetch = root.fetch("person", JoinType.LEFT);
                personFetch.fetch("education", JoinType.LEFT);
                personFetch.fetch("address", JoinType.LEFT);
                personFetch.fetch("contact", JoinType.LEFT);

                // Fetch dos responsáveis e da pessoa/contato de cada responsável
                Fetch<StudentModel, StudentGuardianModel> studenGuardianFetch = root.fetch("guardians", JoinType.LEFT);
                Fetch<StudentGuardianModel, LegalGuardianModel> guardianFetch = studenGuardianFetch.fetch("guardian", JoinType.LEFT);
                Fetch<LegalGuardianModel, IndividualPersonModel> personGuardianFetch = guardianFetch.fetch("person", JoinType.LEFT);
                personGuardianFetch.fetch("contact", JoinType.LEFT);
                
                // Fetch dos relacionamentos OneToOne do estudante para evitar N+1
                root.fetch("homeCondition", JoinType.LEFT);
                root.fetch("healthRecord", JoinType.LEFT);
            }
            return criteriaBuilder.conjunction();
        };
    }
}
