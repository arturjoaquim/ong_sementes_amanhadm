package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.adapter;

import br.ong.sementesamanha.erp.modules.education.domain.entities.LegalGuardian;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.LegalGuardianRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.LegalGuardianMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.LegalGuardianModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.LegalGuardianJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LegalGuardianJpaAdapter implements LegalGuardianRepository {

    private final LegalGuardianJpaRepository jpaRepository;
    private final LegalGuardianMapper mapper;

    public LegalGuardianJpaAdapter(LegalGuardianJpaRepository jpaRepository, LegalGuardianMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public LegalGuardian save(LegalGuardian guardian) {
        LegalGuardianModel model = Optional.ofNullable(guardian.getId())
                .flatMap(jpaRepository::findById)
                .orElseGet(LegalGuardianModel::new);

        mapper.updateModelFromDomain(model, guardian);
        LegalGuardianModel saved = jpaRepository.save(model);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<LegalGuardian> findByPersonId(Long personId) {
        return jpaRepository.findByPersonId(personId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<LegalGuardian> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}
