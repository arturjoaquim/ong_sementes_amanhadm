package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.UpdateEmployeeDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Employee;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.pessoas.IndividualPersonModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.rh.EmployeeModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.sistema.UserModel;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toDomain(EmployeeModel model) {
        if (model == null) return null;
        Employee domain = new Employee();
        domain.setId(model.getId());
        if (model.getPerson() != null) domain.setPersonId(model.getPerson().getId());
        if (model.getSystemUser() != null) domain.setSystemUserId(model.getSystemUser().getId());
        domain.setPositionId(model.getPositionId());
        return domain;
    }

    public EmployeeModel toModel(Employee domain) {
        if (domain == null) return null;
        EmployeeModel model = new EmployeeModel();
        model.setId(domain.getId());
        model.setPositionId(domain.getPositionId());

        if (domain.getPersonId() != null) {
            IndividualPersonModel person = new IndividualPersonModel();
            person.setId(domain.getPersonId());
            model.setPerson(person);
        }

        if (domain.getSystemUserId() != null) {
            UserModel user = new UserModel();
            user.setId(domain.getSystemUserId());
            model.setSystemUser(user);
        }
        
        return model;
    }

    public Employee toDomain(UpdateEmployeeDTO dto) {
        if (dto == null) return null;
        Employee employee = new Employee();
        employee.setPositionId(dto.positionId());
        employee.setSystemUserId(dto.systemUserId());
        return employee;
    }
}
