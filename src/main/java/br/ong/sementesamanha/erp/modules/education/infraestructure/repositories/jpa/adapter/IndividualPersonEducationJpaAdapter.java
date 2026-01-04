package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.adapter;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPersonEducation;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.IndividualPersonEducationRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.IndividualPersonEducationMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.IndividualPersonEducationModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.IndividualPersonEducationJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IndividualPersonEducationJpaAdapter implements IndividualPersonEducationRepository {

    private final IndividualPersonEducationJpaRepository jpaRepository;
    private final IndividualPersonEducationMapper mapper;

    public IndividualPersonEducationJpaAdapter(IndividualPersonEducationJpaRepository jpaRepository, IndividualPersonEducationMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public IndividualPersonEducation save(IndividualPersonEducation education) {
        IndividualPersonEducationModel model = mapper.toModel(education);
        IndividualPersonEducationModel savedModel = jpaRepository.save(model);
        return mapper.toDomain(savedModel);
    }
}
