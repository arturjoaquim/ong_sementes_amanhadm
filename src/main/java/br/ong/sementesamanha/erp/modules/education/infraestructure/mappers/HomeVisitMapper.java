package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.social.HomeVisitResponseDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.HomeVisit;
import org.springframework.stereotype.Component;

@Component
public class HomeVisitMapper {

    public HomeVisitResponseDTO toDTO(HomeVisit entity) {
        if (entity == null) return null;

        return new HomeVisitResponseDTO(
            entity.getId(),
            entity.getVisitDate().toLocalDate(),
            entity.getSummary(),
            entity.getFullReport(),
            entity.getCreatedBy()
        );
    }
}
