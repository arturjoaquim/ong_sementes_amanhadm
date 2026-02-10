package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.student.StudentNoteResponseDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentNote;
import org.springframework.stereotype.Component;

@Component
public class StudentNoteMapper {

    public StudentNoteResponseDTO toDTO(StudentNote entity) {
        if (entity == null) return null;
        return new StudentNoteResponseDTO(
            entity.getId(),
            entity.getPositive(),
            entity.getSummary(),
            entity.getFullDescription(),
            entity.getOccurrenceDate(),
            entity.getRegisteredBy() != null ? entity.getRegisteredBy().getId() : null
        );
    }
}
