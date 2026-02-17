package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories;

import br.ong.sementesamanha.erp.modules.education.domain.entities.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
    @Query("SELECT COUNT(w) FROM Workshop w WHERE SIZE(w.enrollments) >= w.enrollmentLimit")
    long countFullWorkshops();
}
