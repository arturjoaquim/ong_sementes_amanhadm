package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.adapter;

import br.ong.sementesamanha.erp.modules.education.domain.entities.Workshop;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.WorkshopRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.WorkshopMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.WorkshopModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.WorkshopJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class WorkshopJpaAdapter implements WorkshopRepository {

    private final WorkshopJpaRepository jpaRepository;
    private final WorkshopMapper mapper;

    public WorkshopJpaAdapter(WorkshopJpaRepository jpaRepository, WorkshopMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Workshop save(Workshop workshop) {
        WorkshopModel model = mapper.toModel(workshop);
        WorkshopModel saved = jpaRepository.save(model);
        return mapper.toDomain(saved);
    }
}
