package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.UpdateEmployeeDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toDomain(UpdateEmployeeDTO dto) {
        if (dto == null) return null;
        Employee employee = new Employee();
        employee.setPositionId(dto.positionId());
        return employee;
    }
}
