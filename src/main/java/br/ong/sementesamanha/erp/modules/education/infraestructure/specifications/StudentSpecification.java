package br.ong.sementesamanha.erp.modules.education.infraestructure.specifications;

import br.ong.sementesamanha.erp.modules.education.domain.entities.*;
import br.ong.sementesamanha.erp.modules.education.domain.filters.StudentFilter;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StudentSpecification {

    public static Specification<Student> withFilter(StudentFilter filter) {
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

    public static Specification<Student> fetchForPreview() {
        return (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                query.distinct(true);

                Fetch<Student, IndividualPerson> personFetch = root.fetch("person", JoinType.INNER);
                Fetch<IndividualPerson, Person> basePersonFetch = personFetch.fetch("basePerson", JoinType.INNER);
                personFetch.fetch("education", JoinType.LEFT);
                basePersonFetch.fetch("address", JoinType.LEFT);
                basePersonFetch.fetch("contact", JoinType.LEFT);

                // Fetch dos responsáveis e da pessoa/contato de cada responsável
                Fetch<Student, StudentGuardian> studentGuardianFetch = root.fetch("guardians", JoinType.LEFT);
                Fetch<StudentGuardian, LegalGuardian> guardianFetch = studentGuardianFetch.fetch("guardian", JoinType.LEFT);
                Fetch<LegalGuardian, IndividualPerson> personGuardianFetch = guardianFetch.fetch("person", JoinType.LEFT);
                personGuardianFetch.fetch("basePerson", JoinType.LEFT).fetch("contact", JoinType.LEFT);
                
                root.fetch("homeCondition", JoinType.LEFT);
                root.fetch("healthRecord", JoinType.LEFT);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Student> fetchForDetails() {
        return (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                query.distinct(true);

                // Person e filhos
                Fetch<Student, IndividualPerson> personFetch = root.fetch("person", JoinType.INNER);
                Fetch<IndividualPerson, Person> basePersonFecth = personFetch.fetch("basePerson", JoinType.INNER);
                personFetch.fetch("education", JoinType.LEFT);
                basePersonFecth.fetch("address", JoinType.LEFT);
                basePersonFecth.fetch("contact", JoinType.LEFT);
                basePersonFecth.fetch("documents", JoinType.INNER);

                // Guardians e filhos
                Fetch<Student, StudentGuardian> studentGuardianFetch = root.fetch("guardians", JoinType.LEFT);
                Fetch<StudentGuardian, LegalGuardian> guardianFetch = studentGuardianFetch.fetch("guardian", JoinType.LEFT);
                Fetch<LegalGuardian, IndividualPerson> personGuardianFetch = guardianFetch.fetch("person", JoinType.LEFT);
                personGuardianFetch.fetch("basePerson", JoinType.LEFT).fetch("contact", JoinType.LEFT);
                
                // OneToOne
                root.fetch("homeCondition", JoinType.LEFT);
                
                // HealthRecord e filhos (agora Sets, então fetch é seguro)
                Fetch<Student, StudentHealth> healthFetch = root.fetch("healthRecord", JoinType.LEFT);
                healthFetch.fetch("medications", JoinType.LEFT);
                healthFetch.fetch("treatments", JoinType.LEFT);

                // OneToMany (agora Sets)
                root.fetch("occurrences", JoinType.LEFT);
                root.fetch("socialInteractions", JoinType.LEFT);
                
                Fetch<Student, WorkshopParticipant> workshopFetch = root.fetch("workshopParticipations", JoinType.LEFT);
                workshopFetch.fetch("workshop", JoinType.LEFT);
            }
            return criteriaBuilder.conjunction();
        };
    }
}
