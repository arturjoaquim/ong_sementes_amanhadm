package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories;

import br.ong.sementesamanha.erp.modules.education.domain.entities.WorkshopEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkshopEnrollmentRepository extends JpaRepository<WorkshopEnrollment, Long> {
    Optional<WorkshopEnrollment> findByStudentIdAndWorkshopId(Long studentId, Long workshopId);
}
