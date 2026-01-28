package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.CreateEmployeeDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.UpdateEmployeeDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Employee;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.EmployeeRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.enums.LookupTypeEnum;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.EmployeeMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.IndividualPersonMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.UserJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final IndividualPersonService personService;
    private final IndividualPersonMapper personMapper;
    private final UserJpaRepository userRepository;
    private final LookupService lookupService;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository,
                           IndividualPersonService personService,
                           IndividualPersonMapper personMapper,
                           UserJpaRepository userRepository,
                           LookupService lookupService,
                           EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.personService = personService;
        this.personMapper = personMapper;
        this.userRepository = userRepository;
        this.lookupService = lookupService;
        this.employeeMapper = employeeMapper;
    }

    @Transactional
    public Employee create(CreateEmployeeDTO dto) {
        // Validações
        if (lookupService.getLookupAsMap(LookupTypeEnum.POSITION).get(dto.positionId()) == null) {
            throw new IllegalArgumentException("Position not found with id: " + dto.positionId());
        }
        if (dto.systemUserId() != null) {
            userRepository.findById(dto.systemUserId())
                    .orElseThrow(() -> new IllegalArgumentException("System User not found with id: " + dto.systemUserId()));
        }

        IndividualPerson person = personMapper.toDomain(dto.person());
        IndividualPerson savedPerson = personService.create(person);

        Employee employee = new Employee();
        employee.setPersonId(savedPerson.getId());
        employee.setPositionId(dto.positionId());
        employee.setSystemUserId(dto.systemUserId());

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(Long id, UpdateEmployeeDTO dto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        if (dto.positionId() != null) {
            if (lookupService.getLookupAsMap(LookupTypeEnum.POSITION).get(dto.positionId()) == null) {
                throw new IllegalArgumentException("Position not found with id: " + dto.positionId());
            }
            existingEmployee.setPositionId(dto.positionId());
        }

        if (dto.systemUserId() != null) {
            userRepository.findById(dto.systemUserId())
                    .orElseThrow(() -> new IllegalArgumentException("System User not found with id: " + dto.systemUserId()));
            existingEmployee.setSystemUserId(dto.systemUserId());
        }

        if (dto.person() != null && existingEmployee.getPersonId() != null) {
            personService.update(existingEmployee.getPersonId(), dto.person());
        }

        return employeeRepository.save(existingEmployee);
    }
}
