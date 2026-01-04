package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa;

import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.LegalGuardianModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LegalGuardianJpaRepository extends JpaRepository<LegalGuardianModel, Long> {
    Optional<LegalGuardianModel> findByPersonId(Long personId);
}
