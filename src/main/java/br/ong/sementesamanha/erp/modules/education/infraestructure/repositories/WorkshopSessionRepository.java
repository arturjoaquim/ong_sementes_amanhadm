package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories;

import br.ong.sementesamanha.erp.modules.education.domain.entities.WorkshopSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopSessionRepository extends JpaRepository<WorkshopSession, Long> {
	List<WorkshopSession> findTop5ByOrderByCreatedAtDesc();
}
