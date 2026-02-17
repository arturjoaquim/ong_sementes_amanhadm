package br.ong.sementesamanha.erp.modules.education.infraestructure.mappers;

import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.EmployeePreviewResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.EmployeeResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.UpdateEmployeeDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    private final IndividualPersonMapper personMapper;

    public EmployeeMapper(IndividualPersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public Employee toDomain(UpdateEmployeeDTO dto) {
        if (dto == null) return null;
        Employee employee = new Employee();
        employee.setPositionId(dto.positionId());
        return employee;
    }

    public EmployeeResponseDTO toDTO(Employee entity) {
        if (entity == null) return null;
        return new EmployeeResponseDTO(
            entity.getId(),
            personMapper.toDTO(entity.getPerson()),
            entity.getPositionId(),
            entity.getSystemUser() != null ? entity.getSystemUser().getId() : null
        );
    }

    public EmployeePreviewResponseDTO toPreview(Employee entity) {
        if (entity == null) return null;

        String email = "N/A";
        String phone = "N/A";
        if (entity.getPerson() != null && entity.getPerson().getBasePerson() != null && entity.getPerson().getBasePerson().getContact() != null) {
            email = entity.getPerson().getBasePerson().getContact().getEmail();
            phone = entity.getPerson().getBasePerson().getContact().getMobilePhone();
        }

        return new EmployeePreviewResponseDTO(
            entity.getId(),
            entity.getPerson() != null ? entity.getPerson().getPersonName() : "N/A",
            entity.getPositionId(),
            email,
            phone
        );
    }
}
