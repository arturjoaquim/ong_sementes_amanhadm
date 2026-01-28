package br.ong.sementesamanha.erp.modules.education.infraestructure.specifications;

import br.ong.sementesamanha.erp.modules.education.domain.filters.StudentFilter;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.*;
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

                Fetch<StudentModel, IndividualPersonModel> personFetch = root.fetch("person", JoinType.INNER);
                personFetch.fetch("education", JoinType.INNER);
                personFetch.fetch("address", JoinType.LEFT);
                personFetch.fetch("contact", JoinType.LEFT);

                // Fetch dos responsáveis e da pessoa/contato de cada responsável
                Fetch<StudentModel, StudentGuardianModel> studenGuardianFetch = root.fetch("guardians", JoinType.INNER);
                Fetch<StudentGuardianModel, LegalGuardianModel> guardianFetch = studenGuardianFetch.fetch("guardian", JoinType.INNER);
                Fetch<LegalGuardianModel, IndividualPersonModel> personGuardianFetch = guardianFetch.fetch("person", JoinType.INNER);
                personGuardianFetch.fetch("contact", JoinType.INNER);
                
                root.fetch("homeCondition", JoinType.LEFT);
                root.fetch("healthRecord", JoinType.LEFT);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<StudentModel> fetchForDetails() {
        return (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                query.distinct(true);

                // Person e filhos
                Fetch<StudentModel, IndividualPersonModel> personFetch = root.fetch("person", JoinType.INNER);
                personFetch.fetch("education", JoinType.INNER);
                personFetch.fetch("address", JoinType.LEFT);
                personFetch.fetch("contact", JoinType.LEFT);
                personFetch.fetch("documents", JoinType.INNER);

                // Guardians e filhos
                Fetch<StudentModel, StudentGuardianModel> studentGuardianFetch = root.fetch("guardians", JoinType.INNER);
                Fetch<StudentGuardianModel, LegalGuardianModel> guardianFetch = studentGuardianFetch.fetch("guardian", JoinType.INNER);
                Fetch<LegalGuardianModel, IndividualPersonModel> personGuardianFetch = guardianFetch.fetch("person", JoinType.INNER);
                personGuardianFetch.fetch("contact", JoinType.INNER);
                
                // OneToOne
                root.fetch("homeCondition", JoinType.LEFT);
                
                // HealthRecord e filhos (agora Sets, então fetch é seguro)
                Fetch<StudentModel, StudentHealthModel> healthFetch = root.fetch("healthRecord", JoinType.LEFT);
                healthFetch.fetch("medications", JoinType.LEFT);
                healthFetch.fetch("treatments", JoinType.LEFT);

                // OneToMany (agora Sets)
                root.fetch("occurrences", JoinType.LEFT);
                root.fetch("socialInteractions", JoinType.LEFT);
                
                Fetch<StudentModel, WorkshopParticipantModel> workshopFetch = root.fetch("workshopParticipations", JoinType.LEFT);
                workshopFetch.fetch("workshop", JoinType.LEFT);
            }
            return criteriaBuilder.conjunction();
        };
    }
}
