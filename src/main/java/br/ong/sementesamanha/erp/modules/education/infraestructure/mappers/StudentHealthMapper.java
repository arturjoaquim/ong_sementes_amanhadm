package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.domain.entities.StudentHealth;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.academico.StudentHealthModel;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;

@Component
public class StudentHealthMapper {

    public StudentHealth toDomain(StudentHealthModel model) {
        if (model == null) return null;
        StudentHealth domain = new StudentHealth();
        domain.setId(model.getId());
        domain.setUbsName(model.getUbsReference());
        domain.setUseGlasses(model.getWearsGlasses());
        Instant expirationDate = model.getInfoExpirationDate().atStartOfDay(ZoneId.systemDefault()).toInstant();
        domain.setInformationExpirationDate(Date.from(expirationDate));
        // Mapear listas de medicamentos e tratamentos se necessário
        return domain;
    }
}
