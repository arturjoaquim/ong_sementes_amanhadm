package br.ong.sementesamanha.erp.modules.education.domain.ports.repository;

import br.ong.sementesamanha.erp.modules.education.domain.entities.LegalGuardian;
import java.util.Optional;

public interface LegalGuardianRepository {
    LegalGuardian save(LegalGuardian guardian);
    Optional<LegalGuardian> findByPersonId(Long personId);
    Optional<LegalGuardian> findById(Long id);
}
