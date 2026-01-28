package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.WorkshopModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopJpaRepository extends JpaRepository<WorkshopModel, Long> {
}
