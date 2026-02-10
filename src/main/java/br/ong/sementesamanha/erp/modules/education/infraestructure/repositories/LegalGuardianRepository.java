package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories;

import br.ong.sementesamanha.erp.modules.education.domain.entities.LegalGuardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LegalGuardianRepository extends JpaRepository<LegalGuardian, Long> {
    Optional<LegalGuardian> findByPersonId(Long personId);
}
